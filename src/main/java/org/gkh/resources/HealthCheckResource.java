package org.gkh.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.gkh.core.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.Auth;

@Path("/healthcheck")
public class HealthCheckResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckResource.class);

    @GET
    @Path("/ping")
    public Response getPingResponse(@Auth User user) {
        LOGGER.info("User {} pinged us", user.getName());
        return Response.ok().build();
    }
}
