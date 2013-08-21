package com.ciber.adm.poc.hybridmobile.services;

import com.ciber.adm.poc.hybridmobile.domain.Customer;
import com.ciber.adm.poc.hybridmobile.domain.HybridmobileUserDetails;

public interface CustomerService {

	public Customer findCustomerWithUsername(String username);
    public HybridmobileUserDetails createCustomer(Customer customer, String username, String passwd);

}
