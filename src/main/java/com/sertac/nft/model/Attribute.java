package com.sertac.nft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attribute {
	@JsonProperty("trait_type")
	private String traitType;
	private String value;
	@JsonProperty("display_type")
	private String displayType;

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

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

}
