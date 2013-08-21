package com.ciber.adm.poc.hybridmobile.services;

public class NotAvailableException extends Exception {

    private String errorMessge;

    public NotAvailableException(String errorMessge) {
        this.errorMessge = errorMessge;
    }

    public String getErrorMessge() {
        return errorMessge;
    }

}
