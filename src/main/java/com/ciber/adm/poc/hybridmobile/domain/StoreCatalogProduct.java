package com.ciber.adm.poc.hybridmobile.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class StoreCatalogProduct {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private Double price;
    
    @Basic(optional = false)
    private Boolean available;
    
    @ManyToOne
    private StoreProduct storeProduct;
    
    @ManyToOne
    private Catalog catalog;
    
	public StoreCatalogProduct(Double price, Boolean available, StoreProduct storeProduct, Catalog catalog) {
		this.price = price;
		this.available = available;
		this.storeProduct = storeProduct;
		this.catalog = catalog;
	}

	public Long getId() {
		return id;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setName(Double price) {
		this.price = price;
	}
	
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	

	public StoreProduct getStoreProduct() {
		return storeProduct;
	}

	public void setStoreProduct(StoreProduct storeProduct) {
		this.storeProduct = storeProduct;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
