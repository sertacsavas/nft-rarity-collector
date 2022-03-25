package com.sertac.nft.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.nft.model.entity.AttributeEntity;
import com.sertac.nft.model.entity.CollectionAttributeEntity;
import com.sertac.nft.model.entity.CollectionEntity;
import com.sertac.nft.repository.CollectionAttributeRepository;

@Service
@Transactional
public class CollectionAttributeService {

	@Autowired
	CollectionAttributeRepository collectionAttributeRepository;

	@Autowired
	CollectionService collectionService;

	@Autowired
	AttributeService attributeService;

	public List<CollectionAttributeEntity> refreshCollectionAttributes(Long collectionId) {
		CollectionEntity collectionEntity = collectionService.findCollectionById(collectionId);

		Map<CollectionAttributeEntity, Long> collectionAttributeEntityMap = new HashMap<CollectionAttributeEntity, Long>();

		if (collectionEntity.getCollectionAttributes() != null) {
			collectionEntity.getCollectionAttributes().stream()
					.forEach(a -> collectionAttributeEntityMap.put(a, new Long(0)));
		}

		attributeService.findAttributeEntitiesByAssetCollectionId(collectionId).stream()
				.forEach(b -> incrementCollectionAttributeEntityMap(collectionAttributeEntityMap, b, collectionEntity));

//		collectionEntity.getAssets().stream().forEach(a -> a.getAttributes().stream().forEach(
//				b -> incrementCollectionAttributeEntityMap(collectionAttributeEntityMap, b, collectionEntity)));

		List<CollectionAttributeEntity> collectionAttributeEntityList = new ArrayList<CollectionAttributeEntity>(
				collectionAttributeEntityMap.keySet());

		collectionAttributeEntityList.stream().forEach(a -> {
			a.setTotalCount(collectionAttributeEntityMap.get(a));
			a.setRarityPercentage(a.calculateRarityPercentage());
		});
		collectionAttributeRepository.saveAll(collectionAttributeEntityList);
		return collectionAttributeEntityList;

	}

	private void incrementCollectionAttributeEntityMap(
			Map<CollectionAttributeEntity, Long> collectionAttributeEntityMap, AttributeEntity attributeEntity,
			CollectionEntity collectionEntity) {
		CollectionAttributeEntity collectionAttributeEntity = new CollectionAttributeEntity();
		collectionAttributeEntity.setTraitType(attributeEntity.getTraitType());
		collectionAttributeEntity.setValue(attributeEntity.getValue());
		collectionAttributeEntity.setCollection(collectionEntity);

		Long count = collectionAttributeEntityMap.get(collectionAttributeEntity);
		collectionAttributeEntityMap.put(collectionAttributeEntity, count != null ? count + 1 : 1);
	}
}
