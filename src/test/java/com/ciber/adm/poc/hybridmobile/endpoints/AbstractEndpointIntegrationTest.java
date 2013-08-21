package com.ciber.adm.poc.hybridmobile.endpoints;

import com.ciber.adm.poc.hybridmobile.domain.HybridmobileUserDetails;
import com.ciber.adm.poc.hybridmobile.test.EntityPersister;
import com.ciber.adm.poc.hybridmobile.util.BootstrapDataPopulator;
import com.ciber.adm.poc.hybridmobile.util.DateUtil;
import com.ciber.adm.poc.hybridmobile.util.EntityRepository;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:/applicationContext.xml", "classpath:/testContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractEndpointIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private Properties configuration;
    private String port;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EntityPersister entityPersister;

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private RestTemplate restTemplate;

    private JSONSerializer jsonSerializer;

    private HybridmobileUserDetails userForAuthentication;

    protected EntityPersister getEntityPersister() {
        return entityPersister;
    }

    protected EntityRepository getEntityRepository() {
        return entityRepository;
    }

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

    protected HybridmobileUserDetails getUserForAuthentication() {
        return userForAuthentication;
    }

    @Before
    public final void setUp() throws Exception {
        entityRepository.cleanup();
        port = (String) configuration.get("port");
        jsonSerializer = createSerializer();
        userForAuthentication = createUserForAuthentication();
    }

    private JSONSerializer createSerializer() {
        JSONSerializer jsonSerializer = new JSONSerializer().exclude("*.class");
        jsonSerializer.transform(new DateTransformer(DateUtil.DATE_PATTERN), Date.class);
        return jsonSerializer;
    }

    private HybridmobileUserDetails createUserForAuthentication() {
        HybridmobileUserDetails user;
        try {
            user = (HybridmobileUserDetails) entityManager.createQuery("Select u from RylcUserDetails u where u.username = :username")
                    .setParameter("username", BootstrapDataPopulator.TEST_USER_USERNAME)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = entityPersister.createUser(BootstrapDataPopulator.TEST_USER_USERNAME, BootstrapDataPopulator.TEST_USER_PASSWORD);
        }
        return user;
    }

    protected void prepareRestTemplateForAuthentication(String username, String password) {
        CommonsClientHttpRequestFactory factory = (CommonsClientHttpRequestFactory) restTemplate.getRequestFactory();
        HttpClient client = factory.getHttpClient();
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        client.getState().setCredentials(AuthScope.ANY, credentials);
    }

    protected String getUriFor(String path) {
        return "http://localhost:" + port + "/hybridmobile-backend/api" + path;
    }

    protected ResponseEntity<String> getJsonResponse(String path, HttpMethod method, String body, Object... pathVariables) {
        return executeRequest(path, String.class, method, body, pathVariables);
    }

    protected String getJsonBody(String path, HttpMethod method, Object... pathVariables) {
        return getJsonResponse(path, method, null, pathVariables).getBody();
    }

    protected String getJson(String path, Object... pathVariables) {
        return getJsonBody(path, HttpMethod.GET, pathVariables);
    }

    private <T> ResponseEntity<T> executeRequest(String path, Class<T> responseType, HttpMethod method, T body, Object... pathVariables) {
        prepareRestTemplateForAuthentication(userForAuthentication.getUsername(), userForAuthentication.getPassword());
        HttpEntity<T> requestEntity = new HttpEntity<T>(body, requestHeaders());
        return restTemplate.exchange(getUriFor(path), method, requestEntity, responseType, pathVariables);
    }

    private HttpHeaders requestHeaders() {
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(acceptableMediaTypes);
        return requestHeaders;
    }

    protected void assertJsonEquals(Object entityObject, String path, Object... pathVariables) {
        assertEquals(jsonSerializer.serialize(entityObject), getJson(path, pathVariables));
    }

}
