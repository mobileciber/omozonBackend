package com.ciber.adm.poc.hybridmobile.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class CustomercardBooking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Enumerated(EnumType.STRING)
    private EBookingType bookingType;
	@Basic(optional = false)
	private Integer credits;
	@ManyToOne
	@Basic(optional = true)
	private Invoice invoice;
	
	public CustomercardBooking(EBookingType bookingType, Integer credits, Invoice invoice) {
		this.bookingType = bookingType;
		this.credits = credits;
		this.invoice = invoice;
	}

	public EBookingType getBookingType() {
		return bookingType;
	}

	public void setBookingType(EBookingType bookingType) {
		this.bookingType = bookingType;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public Long getId() {
		return id;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
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
