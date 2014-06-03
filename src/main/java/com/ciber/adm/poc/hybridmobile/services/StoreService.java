package com.ciber.adm.poc.hybridmobile.services;

import java.util.List;

import com.ciber.adm.poc.hybridmobile.domain.Store;

public interface StoreService {

	public Store getStore(int id);
	
	public Store getStore(String city);
	
	public List<Store> getStores();
	
}
