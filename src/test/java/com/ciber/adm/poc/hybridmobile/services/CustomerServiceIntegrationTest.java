package com.ciber.adm.poc.hybridmobile.services;

import com.ciber.adm.poc.hybridmobile.domain.Customer;
import com.ciber.adm.poc.hybridmobile.domain.HybridmobileUserDetails;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CustomerServiceIntegrationTest extends AbstractTransactionalIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void findCustomerWithUsername_noMatchingCustomer_returnsNull() {
        assertNull(customerService.findCustomerWithUsername("username"));
    }

    @Test
    public void findCustomerWithUsername_matchingCustomer_returnsCustomer() {
        HybridmobileUserDetails user = getEntityPersister().createUser("username", "secret");
        Customer customer = user.getCustomer();
        assertEquals(customer, customerService.findCustomerWithUsername("username"));
    }

}
