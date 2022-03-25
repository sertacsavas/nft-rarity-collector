package com.sertac.nft.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Asset {
	private String name;
	private String description;
	private String image;

	@JsonProperty("external_url")
	private String externalUrl;
	private Long assetNumber;
	private List<Attribute> attributes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Long getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(Long assetNumber) {
		this.assetNumber = assetNumber;
	}

}
