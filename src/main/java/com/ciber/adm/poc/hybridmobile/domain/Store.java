package com.ciber.adm.poc.hybridmobile.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class Store {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Basic(optional = false)
	private String street;
	
	@Basic(optional = false)
	private String city;
	
	@Basic(optional = false)
	private int zipCode;
	
	@Basic(optional = false)
	private double latitude;
	
	@Basic(optional = false)
	private double longitude;
	
	@Basic(optional = true)
	private String openingTimesMoFr;
	
	@Basic(optional = true)
	private String openingTimesSa;
	
	public Store(){
		
	}
	
	public Store(String street, String city, int zipCode, double latitude, double longitude, String openingTimesMoFr, String openingTimesSa) {
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.openingTimesMoFr = openingTimesMoFr;
		this.openingTimesSa = openingTimesSa;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
	
	public Long getId() {
		return id;
	}

	public String getOpeningTimesMoFr() {
		return openingTimesMoFr;
	}

	public void setOpeningTimesMoFr(String openingTimesMoFr) {
		this.openingTimesMoFr = openingTimesMoFr;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getOpeningTimesSa() {
		return openingTimesSa;
	}

	public void setOpeningTimesSa(String openingTimesSa) {
		this.openingTimesSa = openingTimesSa;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
