package com.patrik.blogg.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy(){
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager(){
        return new HttpSessionManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/api/post","/api/post/{id}","/api/postbycategory/{id}", "/api/category").hasAnyRole("user","admin")
                .antMatchers("/api/author","/api/newauthor", "/api/author/{id}", "/api/editauthor/{id}", "/api/deleteauthor/{id}").hasRole("admin")
                .antMatchers("/api/newcategory", "/api/category/{id}", "/api/editcategory/{id}", "/api/deletecategory/{id}").hasRole("admin")
                .antMatchers("/api/comment","/api/newcomment", "/api/comment/{id}", "/api/editcomment/{id}", "/api/deletecomment/{id}").hasAnyRole("user","admin")
                .antMatchers("/api/newpost","/api/editpost/{id}", "/api/deletepost/{id}","/post/comments/{id}").hasRole("admin")
                .antMatchers("/api/users","/api/newuser", "/api/users/{id}", "/api/user/{id}", "/api/deleteuser/{id}").hasAnyRole("user","admin")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();
        http.csrf().disable().cors().configurationSource(request -> corsFilter());
        http.headers().frameOptions().disable();

    }

    @Bean
    public CorsConfiguration corsFilter() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "PUT"));
        config.setAllowedHeaders(List.of("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin"));
        config.addAllowedHeader("Authorization");
        return config;
    }
}
