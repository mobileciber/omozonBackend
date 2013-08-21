package com.ciber.adm.poc.hybridmobile.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "omozonorders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Temporal(TemporalType.DATE)
    @Basic(optional = false)
	private Date orderDate;
	@Basic(optional = false)
    private Double grandtotal;
	@Basic(optional = false)
    private Double additionalCosts; //delivery + insurence + wrapping + guarantee etc.
	@Basic(optional = false)
    private Double total;
	@Basic(optional = false)
    private Double vat; // only applicable for selfdelivery, otherwise it is a orderline
	@Enumerated(EnumType.STRING)
    private EVatRate vatRate;
	@ManyToOne
	@Basic(optional = false)
    private Customer customer;
	@OneToMany
	private List<Orderitem> orderitems;
	
	public Order(){
		
	}
	
	public Order(Date orderDate, Double grandtotal, Double additionalCosts,
			Double total, Double vat, EVatRate vatRate, Customer customer, List<Orderitem> orderitems) {
		this.orderDate = orderDate;
		this.grandtotal = grandtotal;
		this.additionalCosts = additionalCosts;
		this.total = total;
		this.vat = vat;
		this.vatRate = vatRate;
		this.customer = customer;
		this.orderitems = orderitems;
	}
	public Long getId() {
		return id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Double getGrandtotal() {
		return grandtotal;
	}
	public void setGrandtotal(Double grandtotal) {
		this.grandtotal = grandtotal;
	}
	public Double getAdditionalCosts() {
		return additionalCosts;
	}
	public void setAdditionalCosts(Double additionalCosts) {
		this.additionalCosts = additionalCosts;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
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
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Orderitem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(List<Orderitem> orderitems) {
		this.orderitems = orderitems;
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
