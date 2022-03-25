package com.sertac.nft.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "collection")
public class CollectionEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;
	private String contractAddress;

	@Column(unique = true)
	private String shortName;

	private String apiUrl;
	private String apiUrlSuffix;
	private int minItemId;
	private int maxItemId;

	@OneToMany
	@JoinColumn(name = "collection_id")
	private Set<AssetEntity> assets = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "collection_id")
	private Set<CollectionAttributeEntity> collectionAttributes = new HashSet<>();

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

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public int getMinItemId() {
		return minItemId;
	}

	public void setMinItemId(int minItemId) {
		this.minItemId = minItemId;
	}

	public int getMaxItemId() {
		return maxItemId;
	}

	public void setMaxItemId(int maxItemId) {
		this.maxItemId = maxItemId;
	}

	public Set<AssetEntity> getAssets() {
		return assets;
	}

	public void setAssets(Set<AssetEntity> assets) {
		this.assets = assets;
	}

	public Set<CollectionAttributeEntity> getCollectionAttributes() {
		return collectionAttributes;
	}

	public void setCollectionAttributes(Set<CollectionAttributeEntity> collectionAttributes) {
		this.collectionAttributes = collectionAttributes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CollectionEntity other = (CollectionEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getApiUrlSuffix() {
		return apiUrlSuffix;
	}

	public void setApiUrlSuffix(String apiUrlSuffix) {
		this.apiUrlSuffix = apiUrlSuffix;
	}

}
