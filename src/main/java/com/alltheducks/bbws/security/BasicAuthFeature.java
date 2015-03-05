package com.alltheducks.bbws.security;

import com.alltheducks.bbws.config.Configuration;
import com.alltheducks.configutils.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import java.lang.reflect.Method;

/**
 * Created by Wiley Fuller on 5/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class BasicAuthFeature implements DynamicFeature {
    final Logger logger = LoggerFactory.getLogger(BasicAuthFeature.class);

    ConfigurationService<Configuration> configService;

    @Inject
    public BasicAuthFeature(ConfigurationService<Configuration> configService) {
        this.configService = configService;
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        logger.debug("Context Class: {}", resourceInfo.getResourceClass().getName());
        logger.debug("Context Method: {}", resourceInfo.getResourceMethod().getName());
        logger.debug("Config Service: {}", configService);
        Class c = resourceInfo.getResourceClass();
        Method m = resourceInfo.getResourceMethod();

        if (c.isAnnotationPresent(RequiresAuthentication.class) ||
                m.isAnnotationPresent(RequiresAuthentication.class)) {
            ConfigurableBasicAuthFilter filter = new ConfigurableBasicAuthFilter(configService);
            context.register(filter);
        }
    }
}
