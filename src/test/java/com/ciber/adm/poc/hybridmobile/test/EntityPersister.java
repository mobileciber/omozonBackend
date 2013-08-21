package com.ciber.adm.poc.hybridmobile.test;

import com.ciber.adm.poc.hybridmobile.domain.*;

import java.math.BigDecimal;
import java.util.List;


public interface EntityPersister {

    Customer createCustomer(String email);
        
    HybridmobileUserDetails createUser(String username, String password);

//    List<Rental> createRentalsFor(Customer user);
//
//    List<City> createCities(String... cityNames);
//
//    Car createAvailableCarFor(String cityName, BigDecimal maxPrice);

}
