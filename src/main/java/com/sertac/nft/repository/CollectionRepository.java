package com.sertac.nft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sertac.nft.model.entity.CollectionEntity;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

}
