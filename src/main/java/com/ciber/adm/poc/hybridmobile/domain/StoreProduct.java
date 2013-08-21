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
public class StoreProduct {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private Double price;
    
    @Basic(optional = false)
    private Boolean available;
    
    @ManyToOne
    private Product product;
    
    @ManyToOne
    private Store store;
    
	public StoreProduct(Double price, Boolean available, Product product, Store store) {
		this.price = price;
		this.available = available;
		this.product = product;
		this.store = store;
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
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
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
