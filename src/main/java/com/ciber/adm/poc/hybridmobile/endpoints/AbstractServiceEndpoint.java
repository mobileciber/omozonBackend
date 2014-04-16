package com.ciber.adm.poc.hybridmobile.endpoints;

import com.ciber.adm.poc.hybridmobile.util.DateUtil;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractServiceEndpoint {

    private JSONSerializer jsonSerializer;

    public AbstractServiceEndpoint() {
        this.jsonSerializer = new JSONSerializer().exclude("*.class");
        this.jsonSerializer.transform(new DateTransformer(DateUtil.DATE_PATTERN), Date.class);
    }

    protected Response createStatusResponse(Response.Status status, String statusMessage) {
    	JSONSerializer jsonSerializer = new JSONSerializer();
    	Map<String, Object> parameter = new HashMap<String, Object>();
    	parameter.put("statusMessage", statusMessage);
    	statusMessage = "{\"message\" : \"" + statusMessage + "\"}";
        return Response
                .status(status)
                .type("application/json")
                .entity(statusMessage)
                .build();
    }

    protected Response createOkResponseFor(Object domainObject) {
        return Response.ok(jsonSerializer.serialize(domainObject)).header("Cache-Control", "no-cache").header("Pragma", "no-cache").build();
    }
}
