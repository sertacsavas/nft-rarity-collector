package com.sertac.nft.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "asset", uniqueConstraints = { @UniqueConstraint(columnNames = { "collection_id", "assetNumber" }) }

)
public class AssetEntity implements Comparable<AssetEntity> {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String image;
	private String externalUrl;
	private Long assetNumber;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "asset_id")
	private List<AttributeEntity> attributes;

	@ManyToOne
	@JoinColumn(name = "collection_id", nullable = false)
	private CollectionEntity collection;

	private Double rarityScore;
	private Integer ranking;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<AttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeEntity> attributes) {
		this.attributes = attributes;
	}

	public CollectionEntity getCollection() {
		return collection;
	}

	public void setCollection(CollectionEntity collection) {
		this.collection = collection;
	}

	public Long getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(Long assetNumber) {
		this.assetNumber = assetNumber;
	}

	public Double getRarityScore() {
		return rarityScore;
	}

	public void setRarityScore(Double rarityScore) {
		this.rarityScore = rarityScore;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	@Override
	public int compareTo(AssetEntity o) {
		return Double.compare(o.getRarityScore(), this.rarityScore);
	}

}
