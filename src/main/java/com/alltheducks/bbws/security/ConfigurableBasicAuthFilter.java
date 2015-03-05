package com.alltheducks.bbws.security;

import com.alltheducks.bbws.config.Configuration;
import com.alltheducks.configutils.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * Created by Wiley Fuller on 5/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class ConfigurableBasicAuthFilter implements ContainerRequestFilter {
    final Logger logger = LoggerFactory.getLogger(ConfigurableBasicAuthFilter.class);

    ConfigurationService<Configuration> configurationService;
    private final static WebApplicationException unauthorized =
            new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"realm\"")
                            .entity("Resource requires Authentication.").build());


    public ConfigurableBasicAuthFilter(ConfigurationService<Configuration> configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Configuration config = configurationService.loadConfiguration();
        if (config == null) {
            throw unauthorized;
        }

        String expectedUsername = config.getUsername();
        String expectedPassword = config.getPassword();

        if (expectedUsername == null || expectedUsername.isEmpty() ||
                expectedPassword == null || expectedPassword.isEmpty()) {
            throw unauthorized;
        }

        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null) {
            throw unauthorized;
        }

        String[] usernamePassword = decode(authHeader);
        if (usernamePassword == null || usernamePassword.length != 2) {
            throw unauthorized;
        }

        String username = usernamePassword[0];
        String password = usernamePassword[1];

        if (!(username.equals(expectedUsername) && password.equals(expectedPassword))) {
            throw unauthorized;
        }


    }

    public static String[] decode(String auth) {
        //Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
        auth = auth.replaceFirst("[B|b]asic ", "");

        //Decode the Base64 into byte[]
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);

        //If the decode fails in any case
        if(decodedBytes == null || decodedBytes.length == 0){
            return null;
        }

        //Now we can convert the byte[] into a splitted array :
        //  - the first one is login,
        //  - the second one password
        return new String(decodedBytes).split(":", 2);
    }
}
