package com.ciber.adm.poc.hybridmobile.test;

import java.util.ArrayList;

import com.ciber.adm.poc.hybridmobile.domain.Customer;
import com.ciber.adm.poc.hybridmobile.domain.HybridmobileUserDetails;
import com.ciber.adm.poc.hybridmobile.domain.Order;

public class EntityFactory {

    private static final String UNKNOWN = "unknown";

    public static HybridmobileUserDetails newUser(String username, String password) {
        return new HybridmobileUserDetails(username, password, newCustomer(String.format("%s@%s", username, UNKNOWN)));
    }

    public static Customer newCustomer(String email) {
        return new Customer(UNKNOWN, email, UNKNOWN, UNKNOWN, UNKNOWN, new ArrayList<Order>());
    }

}
