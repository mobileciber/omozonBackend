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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class Invoice {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Temporal(TemporalType.DATE)
    @Basic(optional = false)
	private Date date;
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
	private List<Invoiceitem> invoiceitems;
	@OneToOne
	private Customercard customercard;
	@Basic(optional = false)
	private Double totalDiscount;
	@Enumerated(EnumType.STRING)
    private ECurrency currency;
	
	public Invoice(Date date, Double grandtotal, Double additionalCosts,
			Double total, Double vat, EVatRate vatRate, Customer customer, List<Invoiceitem> invoiceitems, Customercard customercard, Double totalDiscount, ECurrency currency) {
		this.date = date;
		this.grandtotal = grandtotal;
		this.additionalCosts = additionalCosts;
		this.total = total;
		this.vat = vat;
		this.vatRate = vatRate;
		this.customer = customer;
		this.invoiceitems = invoiceitems;
		this.customercard = customercard;
		this.totalDiscount = totalDiscount;
		this.currency = currency;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public Long getId() {
		return id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Invoiceitem> getInvoiceitems() {
		return invoiceitems;
	}
	public void setInvoiceitems(List<Invoiceitem> invoiceitems) {
		this.invoiceitems = invoiceitems;
	}
	public Customercard getCustomercard() {
		return customercard;
	}
	public void setCustomercard(Customercard customercard) {
		this.customercard = customercard;
	}
	
	public Double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public ECurrency getCurrency() {
		return currency;
	}
	public void setCurrency(ECurrency currency) {
		this.currency = currency;
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
