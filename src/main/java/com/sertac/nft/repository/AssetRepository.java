package com.sertac.nft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sertac.nft.model.entity.AssetEntity;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {

	List<AssetEntity> getAssetsByCollectionId(Long collectionId);

}
