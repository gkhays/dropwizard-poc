package org.gkh;

import org.gkh.auth.StaticAuthenticator;
import org.gkh.core.User;
import org.gkh.resources.HealthCheckResource;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MyApplication extends Application<MyConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public String getName() {
        return "Dropwizard PoC";
    }

    @Override
    public void initialize(final Bootstrap<MyConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(final MyConfiguration configuration,
            final Environment environment) {
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new StaticAuthenticator())
                        // .setAuthorizer(new StaticAuthorizer())
                        // .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        // environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        environment.jersey().register(new HealthCheckResource());
    }

}
