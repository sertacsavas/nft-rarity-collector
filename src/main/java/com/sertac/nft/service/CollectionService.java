package com.sertac.nft.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.nft.model.entity.CollectionEntity;
import com.sertac.nft.repository.CollectionRepository;

@Service
@Transactional
public class CollectionService {

	@Autowired
	CollectionRepository collectionRepository;

	public void createCollection(CollectionEntity collectionEntity) {
		collectionRepository.save(collectionEntity);
	}

	public CollectionEntity findCollectionById(Long collectionId) {
		return collectionRepository.findById(collectionId).get();
	}

	public List<CollectionEntity> findAll() {
		return collectionRepository.findAll();
	}

}
