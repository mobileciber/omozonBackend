package com.ciber.adm.poc.hybridmobile.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ciber.adm.poc.hybridmobile.domain.Store;

@Service
public class JpaStoreService implements StoreService {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	public Store getStore(String city) {
		try{
			Store store = (Store) entityManager
					.createQuery("select s from Store s where s.city = :city")
					.setParameter("city", city)
					.getSingleResult();
			return store;
		}
		catch(NoResultException ex){
			return null;
		}
	}
	
	@Transactional(readOnly = true)
	public List<Store> getStores() {
		try{
			List<Store> stores = (List<Store>)entityManager
					.createQuery("select s from Store s")
					//.setParameter("", "")
					.getResultList();
			return stores;
		}
		catch(NoResultException ex){
			return null;
		}
	}

}
