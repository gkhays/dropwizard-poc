package org.gkh.auth;

import java.util.Optional;

import org.gkh.core.User;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class StaticAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (credentials.getUsername().equals("admin") && credentials.getPassword().equals("admin")) {
            return Optional.of(new User("admin"));
        } else {
            // return Optional.absent();
            return Optional.empty();
        }
    }

}
