package com.ciber.adm.poc.hybridmobile.services;

import com.ciber.adm.poc.hybridmobile.domain.Customer;
import com.ciber.adm.poc.hybridmobile.domain.HybridmobileUserDetails;
import com.ciber.adm.poc.hybridmobile.util.EntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Service
public class JpaCustomerService implements CustomerService {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private EntityRepository entityRepository;

    @Transactional(readOnly = true)
    public Customer findCustomerWithUsername(String username) {
        try {
            return (Customer) entityManager
                    .createQuery("select u.customer from HybridmobileUserDetails u where u.username = :username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

	public HybridmobileUserDetails createCustomer(Customer customer, String username, String password) {
		HybridmobileUserDetails user = new HybridmobileUserDetails(username, password, customer);
		entityRepository.persist(user);
		return user;
	}

}
