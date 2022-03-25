package com.sertac.nft.mapper;

import java.util.ArrayList;
import java.util.List;

import com.sertac.nft.model.Asset;
import com.sertac.nft.model.Attribute;
import com.sertac.nft.model.Collection;
import com.sertac.nft.model.entity.AssetEntity;
import com.sertac.nft.model.entity.AttributeEntity;
import com.sertac.nft.model.entity.CollectionEntity;

public class ModelMapper {
	public static CollectionEntity collectionToCollectionEntity(Collection collection) {
		CollectionEntity collectionEntity = new CollectionEntity();
		collectionEntity.setApiUrl(collection.getApiUrl());

		collectionEntity.setContractAddress(collection.getContractAddress());
		collectionEntity.setMaxItemId(collection.getMaxItemId());

		collectionEntity.setMinItemId(collection.getMinItemId());
		collectionEntity.setName(collection.getName());
		collectionEntity.setShortName(collection.getShortName());

		return collectionEntity;
	}

	public static AssetEntity assetToAssetEntity(Asset asset) {
		AssetEntity assetEntity = new AssetEntity();

		assetEntity.setAssetNumber(asset.getAssetNumber());

		List<AttributeEntity> attributes = new ArrayList<AttributeEntity>();

		if (asset.getAttributes() != null && asset.getAttributes().size() > 0) {
			asset.getAttributes().stream().forEach(a -> attributes.add(attributeToAttributeEntity(a, assetEntity)));
		}

		assetEntity.setAttributes(attributes);
		assetEntity.setDescription(asset.getDescription());
		assetEntity.setExternalUrl(asset.getExternalUrl());
		assetEntity.setImage(asset.getImage());
		assetEntity.setName(asset.getName());

		return assetEntity;
	}

	public static AttributeEntity attributeToAttributeEntity(Attribute attribute, AssetEntity assetEntity) {
		AttributeEntity attributeEntity = new AttributeEntity();

		attributeEntity.setTraitType(attribute.getTraitType());
		attributeEntity.setValue(attribute.getValue());
		attributeEntity.setAsset(assetEntity);
		attributeEntity.setDisplayType(attribute.getDisplayType());
		return attributeEntity;
	}

}
