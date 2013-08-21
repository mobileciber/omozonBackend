package com.ciber.adm.poc.hybridmobile.services;

import com.ciber.adm.poc.hybridmobile.test.EntityPersister;
import com.ciber.adm.poc.hybridmobile.util.EntityRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:/applicationContext.xml", "classpath:/testContext.xml"})
public abstract class AbstractTransactionalIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private EntityPersister entityPersister;

    @Before
    public final void setUp() {
        entityRepository.cleanup();
    }

    protected EntityRepository getEntityRepository() {
        return entityRepository;
    }

    protected EntityPersister getEntityPersister() {
        return entityPersister;
    }
}
