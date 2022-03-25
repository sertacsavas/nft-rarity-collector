package com.sertac.nft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sertac.nft.model.entity.AttributeEntity;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Long> {
	public List<AttributeEntity> findAttributeEntitiesByAssetCollectionId(Long collectionId);
}
