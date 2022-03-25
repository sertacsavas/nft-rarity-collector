package com.sertac.nft.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attribute")
public class AttributeEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String traitType;
	private String value;

	private String displayType;

	@ManyToOne
	@JoinColumn(name = "asset_id", nullable = false)
	private AssetEntity asset;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTraitType() {
		return traitType;
	}

	public void setTraitType(String traitType) {
		this.traitType = traitType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public AssetEntity getAsset() {
		return asset;
	}

	public void setAsset(AssetEntity asset) {
		this.asset = asset;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

}
