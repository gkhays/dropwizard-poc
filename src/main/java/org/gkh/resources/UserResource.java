package org.gkh.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.gkh.core.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.Auth;
import io.dropwizard.jackson.Jackson;

@Path("/user")
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @GET
    public Response getUserList(@Auth User user) {
        ObjectMapper mapper = Jackson.newObjectMapper();
        List<User> users = new ArrayList<>();
        users.add(new User("John Doe"));
        users.add(new User("Jane Doe"));
        users.add(new User("Jack Doe"));
        LOGGER.info("User {} loging in...", user.getName());

        try {
            return Response.ok(mapper.writeValueAsString(users)).build();
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while processing user list", e);
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
