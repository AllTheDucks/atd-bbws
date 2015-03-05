package com.alltheducks.bbws.ws;

import blackboard.persist.course.CourseDbLoader;
import com.alltheducks.bbws.security.BasicAuthFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.spring.bridge.api.SpringBridge;
import org.jvnet.hk2.spring.bridge.api.SpringIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;

/**
 * Created by Wiley Fuller on 24/02/15.
 * Copyright All the Ducks Pty. Ltd.
 */
@ApplicationPath("resources")
public class BbWebServiceApplication extends ResourceConfig {
    final Logger logger = LoggerFactory.getLogger(BbWebServiceApplication.class);


    @Inject
    public BbWebServiceApplication(ServiceLocator serviceLocator, ServletContext servletContext) {

        // Register a Spring to HK2 Bridge so we can use our Spring beans in Jersey land.
        SpringBridge.getSpringBridge().initializeSpringBridge(serviceLocator);
        final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        SpringIntoHK2Bridge springBridge = serviceLocator.getService(SpringIntoHK2Bridge.class);
        springBridge.bridgeSpringBeanFactory(springContext);


        packages("com.alltheducks.bbws.ws;com.alltheducks.bbws.security");
        register(JacksonJsonProvider.class);
        register(BasicAuthFeature.class);

        logger.info("Started BbWebServiceApplication.");
    }


}