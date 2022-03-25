package com.sertac.nft.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "collection_attribute")
public class CollectionAttributeEntity {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "collection_id", nullable = false)
	private CollectionEntity collection;

	private String traitType;
	private String value;
	private Long totalCount;
	private Double rarityPercentage;

	public double calculateRarityPercentage() {
		return (double) this.totalCount
				/ (double) (this.getCollection().getMaxItemId() - this.getCollection().getMinItemId()) * 100.0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CollectionEntity getCollection() {
		return collection;
	}

	public void setCollection(CollectionEntity collection) {
		this.collection = collection;
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

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collection == null) ? 0 : collection.hashCode());
		result = prime * result + ((traitType == null) ? 0 : traitType.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollectionAttributeEntity other = (CollectionAttributeEntity) obj;
		if (collection == null) {
			if (other.collection != null)
				return false;
		} else if (!collection.equals(other.collection))
			return false;
		if (traitType == null) {
			if (other.traitType != null)
				return false;
		} else if (!traitType.equals(other.traitType))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public Double getRarityPercentage() {
		return rarityPercentage;
	}

	public void setRarityPercentage(Double rarityPercentage) {
		this.rarityPercentage = rarityPercentage;
	}

}
