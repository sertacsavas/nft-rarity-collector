package com.sertac.nft.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.nft.client.MetaDataClient;
import com.sertac.nft.mapper.ModelMapper;
import com.sertac.nft.model.Asset;
import com.sertac.nft.model.entity.AssetEntity;
import com.sertac.nft.model.entity.CollectionAttributeEntity;
import com.sertac.nft.model.entity.CollectionEntity;
import com.sertac.nft.repository.AssetRepository;

@Service
@Transactional
public class AssetService {
	@Autowired
	AssetRepository assetRepository;

	@Autowired
	MetaDataClient metaDataClient;

	@Autowired
	CollectionService collectionService;

	@Autowired
	CollectionAttributeService collectionAttributeService;

	@Autowired
	AttributeService attributeService;

	public void collectAssets(Long collectionId) {
		CollectionEntity collectionEntity = collectionService.findCollectionById(collectionId);
		collectAssetsByCollection(collectionEntity);
	}

	public void collectAssetsByCollection(CollectionEntity collectionEntity) {

		Set<Long> collectedAssets = new HashSet<Long>();
		collectionEntity.getAssets().stream().map(a -> a.getAssetNumber()).forEach(collectedAssets::add);

		List<Long> willBeCollected = new ArrayList<Long>();

		LongStream.rangeClosed(collectionEntity.getMinItemId(), collectionEntity.getMaxItemId())
				.filter(a -> !collectedAssets.contains(a)).forEach(willBeCollected::add);

		ForkJoinPool myPool = new ForkJoinPool(1);

		try {
			myPool.submit(() -> willBeCollected.parallelStream().forEach(a -> getMetaDataAndSave(collectionEntity, a)))
					.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		if (willBeCollected.size() > 0) {
			List<CollectionAttributeEntity> collectionAttributeEntityList = collectionAttributeService
					.refreshCollectionAttributes(collectionEntity.getId());
			refreshRarityScoresAndRankings(collectionEntity.getId(), collectionAttributeEntityList);
		}

	}

	public void refreshRarityScoresAndRankings(Long collectionId,
			List<CollectionAttributeEntity> collectionAttributeEntityList) {
		CollectionEntity collectionEntity = collectionService.findCollectionById(collectionId);
		int collectionSize = collectionEntity.getMaxItemId() - collectionEntity.getMinItemId() + 1;
		List<AssetEntity> assets = getAssetsByCollectionId(collectionId);

		Map<CollectionAttributeEntity, Long> collectionAttributeEntityCountMap = new HashMap<CollectionAttributeEntity, Long>();

		if (collectionAttributeEntityList != null && collectionAttributeEntityList.size() > 0) {
			collectionAttributeEntityList.stream()
					.forEach(a -> collectionAttributeEntityCountMap.put(a, a.getTotalCount()));
		}

		assets.stream().forEach(a -> {
			double rarityScore = a.getAttributes().stream().mapToDouble(b -> {

				CollectionAttributeEntity c = new CollectionAttributeEntity();
				c.setTraitType(b.getTraitType());
				c.setValue(b.getValue());
				c.setCollection(collectionEntity);
				return (double) (collectionSize) / (double) collectionAttributeEntityCountMap.get(c);

			}).sum();

			a.setRarityScore(rarityScore);
		});

		Collections.sort(assets);

		for (int i = 0; i < assets.size(); i++) {
			assets.get(i).setRanking(i + 1);
		}

		assetRepository.saveAll(assets);

	}

	public List<AssetEntity> getAssetsByCollectionId(Long collectionId) {
		return assetRepository.getAssetsByCollectionId(collectionId);
	}

	private void getMetaDataAndSave(CollectionEntity collectionEntity, Long assetNumber) {
		try {
			// Thread.sleep(100);
			Asset asset = metaDataClient.getMetaData(collectionEntity.getApiUrl() + assetNumber
					+ (collectionEntity.getApiUrlSuffix() != null ? collectionEntity.getApiUrlSuffix() : ""));

			AssetEntity assetEntity = ModelMapper.assetToAssetEntity(asset);
			assetEntity.setAssetNumber(assetNumber);
			assetEntity.setCollection(collectionEntity);
			assetRepository.save(assetEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void collectAll() {
		collectionService.findAll().stream().forEach(a -> collectAssetsByCollection(a));
	}

}
