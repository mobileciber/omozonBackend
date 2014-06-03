package com.ciber.adm.poc.hybridmobile.endpoints;


import static java.lang.String.format;

import com.ciber.adm.poc.hybridmobile.domain.*;
import com.ciber.adm.poc.hybridmobile.services.NotAvailableException;
import com.ciber.adm.poc.hybridmobile.services.RentalService;
import com.ciber.adm.poc.hybridmobile.services.StoreService;
import com.ciber.adm.poc.hybridmobile.util.AuthenticationService;
import com.ciber.adm.poc.hybridmobile.util.DateUtil;
import flexjson.JSONDeserializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@Path("stores")
@Produces("application/json")
public class StoreServiceEndpoint extends AbstractServiceEndpoint {

    private static final Log LOG = LogFactory.getLog(StoreServiceEndpoint.class);

    @Autowired
    private StoreService storeService;
    
    @GET
    @Path("")
    public Response getStores(){
    	List<Store> stores = storeService.getStores();
    	if (stores == null) return notFound("");
    	return ok(stores);
    }
    
    @GET
    @Path("{id}")
    public Response getStore(@PathParam("id") int id){
    	Store store = storeService.getStore(id);
    	if (store == null) return notFound(Integer.toString(id));
    	return ok(store);
    }
    
    @GET
    @Path("{city}")
    public Response getStore(@PathParam("city") String city){
    	Store store = storeService.getStore(city);
    	if (store == null) return notFound(city);
    	return ok(store);
    }
    
    private Response notFound(String storeName) {
		return createStatusResponse(Response.Status.NOT_FOUND,
				format("no store with name '%s' found.", storeName));
	}
    
    private Response ok(Store store) {
    	LOG.info("Store found: " + store.getCity());
        return createOkResponseFor(store);
    }
    
    private Response ok(List<Store> stores) {
    	LOG.info("Stores found: " + stores.size());
        return createOkResponseFor(stores);
    }
}
