package com.ciber.adm.poc.hybridmobile.endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ciber.adm.poc.hybridmobile.domain.Customer;
import com.ciber.adm.poc.hybridmobile.domain.Order;
import com.ciber.adm.poc.hybridmobile.services.CustomerService;
import com.ciber.adm.poc.hybridmobile.util.AuthenticationService;

import flexjson.JSONDeserializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static java.lang.String.format;

@Component
@Path("customers")
@Produces("application/json")
public class CustomerServiceEndpoint extends AbstractServiceEndpoint {
	private static final Log LOG = LogFactory.getLog(CustomerServiceEndpoint.class);

	@Autowired
	private AuthenticationService authenticationService;
	
    @Autowired
    private CustomerService customerService;

	@GET
	public Response getCustomerWithUsername(
			@QueryParam("username") String username) {
		Customer customer = customerService.findCustomerWithUsername(username);
		if (customer == null)
			return notFound(username);
		return ok(customer);
	}

	@POST
	@SuppressWarnings({ "unchecked" })
	public Response createCustomer(@RequestBody String requestBody) {
		LOG.debug("requestBody=" + requestBody);
		JSONDeserializer<Map> jsonDeserializer = new JSONDeserializer();
		Map<String, Object> parameter = jsonDeserializer.deserialize(requestBody);
		Customer customer = new Customer(parameter.get("name").toString(), 
				parameter.get("email").toString(), 
				parameter.get("street").toString(), 
				parameter.get("zip").toString(), 
				parameter.get("city").toString(), 
				new ArrayList<Order>());
		customer = this.customerService.createCustomer(customer, parameter.get("username").toString(), parameter.get("passwd").toString()).getCustomer();
		return createStatusResponse(Response.Status.CREATED, "customer created with Id=" + customer.getId());
	}
	
//	@PUT
//	@SuppressWarnings({ "unchecked" })
//	public Response updateCustomer(@RequestBody String requestBody) {
//		LOG.debug("requestBody=" + requestBody);
//		JSONDeserializer<Map> jsonDeserializer = new JSONDeserializer();
//		Map<String, Object> parameter = jsonDeserializer
//				.deserialize(requestBody);
//		Customer customer;
//		try {
//			customer = this.customerService.rentCar(authenticationService
//					.getCurrentUser().getCustomer(), Long.parseLong(parameter
//					.get("carId").toString()), DateUtil.toDate(parameter.get(
//					"startDate").toString()), DateUtil.toDate(parameter.get(
//					"endDate").toString()));
//		} catch (NotAvailableException nAE) {
//			return createStatusResponse(Response.Status.CONFLICT,
//					nAE.getErrorMessge());
//		}
//		return createStatusResponse(Response.Status.CREATED,
//				"customer updated with Id=" + customer.getId());
//	}
	
//	@DELETE
//	public Response deleteCustomerWithUsername(
//			@QueryParam("username") String username) {
//		Customer customer = customerService.deleteCustomerWithUsername(username);
//		if (customer == null)
//			return notFound(username);
//		return ok(customer);
//	}

	private Response notFound(String username) {
		return createStatusResponse(Response.Status.NOT_FOUND,
				format("no customer with username '%s' found.", username));
	}

    private Response ok(Customer customer) {
        return createOkResponseFor(customer);
    }

}
