package br.com.SistemaLanchonete.Config;

import org.glassfish.jersey.server.ResourceConfig;
import org.secnod.shiro.jersey.AuthInjectionBinder;
import org.secnod.shiro.jersey.AuthorizationFilterFeature;
import org.secnod.shiro.jersey.SubjectFactory;

public class ApiApplication extends ResourceConfig {
    public ApiApplication() {
        register(new AuthorizationFilterFeature());
        register(new SubjectFactory());
        register(new AuthInjectionBinder());
        
        packages("br.com.SistemaLanchonete.Resource");
    }
}
