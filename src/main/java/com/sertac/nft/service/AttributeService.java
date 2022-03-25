package com.sertac.nft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.nft.model.entity.AttributeEntity;
import com.sertac.nft.repository.AttributeRepository;

@Service
public class AttributeService {

	@Autowired
	AttributeRepository attributeRepository;

	public List<AttributeEntity> findAttributeEntitiesByAssetCollectionId(Long collectionId) {
		return attributeRepository.findAttributeEntitiesByAssetCollectionId(collectionId);
	}

}
