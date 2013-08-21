package com.ciber.adm.poc.hybridmobile.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class Orderitem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Basic(optional = false)
	private String name;
	@Basic(optional = false)
	private Double singleprice;
	@Basic(optional = false)
    private Double vat;
	@Enumerated(EnumType.STRING)
    private EVatRate vatRate;
	@Basic(optional = false)
	private Integer amount;
	@Basic(optional = false)
    private Double total;
	@Basic(optional = false)
    private String storeProductId;
	@OneToOne
	private Product storeProduct;
	@ManyToOne
	private Order order;
	public Orderitem(String name, Double singleprice, Double vat,
			EVatRate vatRate, Integer amount, Double total,
			String storeProductId, Product storeProduct, Order order) {
		this.name = name;
		this.singleprice = singleprice;
		this.vat = vat;
		this.vatRate = vatRate;
		this.amount = amount;
		this.total = total;
		this.storeProductId = storeProductId;
		this.storeProduct = storeProduct;
		this.order = order;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSingleprice() {
		return singleprice;
	}
	public void setSingleprice(Double singleprice) {
		this.singleprice = singleprice;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public EVatRate getVatRate() {
		return vatRate;
	}
	public void setVatRate(EVatRate vatRate) {
		this.vatRate = vatRate;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getStoreProductId() {
		return storeProductId;
	}
	public void setStoreProductId(String storeProductId) {
		this.storeProductId = storeProductId;
	}
	public Product getStoreProduct() {
		return storeProduct;
	}
	public void setStoreProduct(Product storeProduct) {
		this.storeProduct = storeProduct;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Long getId() {
		return id;
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
