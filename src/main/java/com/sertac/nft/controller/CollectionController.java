package com.sertac.nft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sertac.nft.mapper.ModelMapper;
import com.sertac.nft.model.Collection;
import com.sertac.nft.service.CollectionService;

@RestController
@RequestMapping(path = "/api/collection")
public class CollectionController {

	@Autowired
	CollectionService collectionService;

	@PostMapping("/createCollection")
	public void createCollection(@RequestBody Collection collection) {
		collectionService.createCollection(ModelMapper.collectionToCollectionEntity(collection));
	}
}
