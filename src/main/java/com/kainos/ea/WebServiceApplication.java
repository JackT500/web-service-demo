package com.kainos.ea;

import com.kainos.ea.model.Employee;
import com.kainos.ea.resources.WebService;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class WebServiceApplication extends Application<WebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new WebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "WebService";
    }

    @Override
    public void initialize(final Bootstrap<WebServiceConfiguration> bootstrap) {
        // TODO: application initialization
        //Employee emp = new Employee(1, "Jack", "Thompson", 100000_00,"0101010101", "00-00-00", "PK0987654", "07393322469", "email@email.com");
        bootstrap.addBundle(new SwaggerBundle<WebServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(WebServiceConfiguration configuration) {
                return configuration.getSwagger();
            }
            
        });
    }

    @Override
    public void run(final WebServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new WebService());
    }

}
