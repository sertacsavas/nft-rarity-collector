package com.sertac.nft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sertac.nft.model.entity.CollectionAttributeEntity;

public interface CollectionAttributeRepository extends JpaRepository<CollectionAttributeEntity, Long> {

}
