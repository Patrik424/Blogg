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
                .antMatchers("/api/post").hasAnyRole("user", "admin")
                .antMatchers("/api/post/{id}").hasRole("user")
                .antMatchers("/api/comment").hasRole("user")
                .antMatchers( "/api/newcomment", "/api/editcomment/{id}", "/api/deletecomment/{id}").hasAnyRole("user","admin")
                .antMatchers("/api/newcomment").hasRole("user")
                .antMatchers("/api/comment/{id}").hasRole("admin")
                .antMatchers("/api/newpost").hasRole("admin")
                .antMatchers("/api/editpost/{id}").hasRole("admin")
                .antMatchers("/api/deletepost/{id}").hasRole("admin")
                .antMatchers("/api/users").hasRole("admin")
                .antMatchers("/api/editusers/{id}").hasRole("admin")
                .antMatchers("/api/deleteusers/{id}").hasRole("admin")
                .antMatchers("/api/newuser").hasRole("user")
                .antMatchers("/api/newcategory").hasRole("admin")
                .antMatchers("/api/editcategory/{id}").hasRole("admin")
                .antMatchers("/api/deletecategory/{id}").hasRole("admin")
                .antMatchers("/api/author").hasRole("admin")
                .antMatchers("/api/newauthor").hasRole("admin")
                .antMatchers("/api/author/{id}").hasRole("admin")
                .antMatchers("/api/editauthor/{id}").hasRole("admin")
                .antMatchers("/api/deleteauthor/{id}").hasRole("admin")
                .antMatchers("/api/category").hasAnyRole("user","admin")
                .antMatchers("/api/post/comments/{id}").hasAnyRole("user","admin")
                .antMatchers("/api/title/{title}/comments").hasAnyRole("user","admin")
                .antMatchers("/api/{id}/comments").hasAnyRole("user","admin")
                .antMatchers("/api/{id}/comments").hasAnyRole("user","admin")
                .antMatchers("/api/{id}/comments").hasAnyRole("user","admin")
                .antMatchers("/api/users").hasAnyRole("user","admin")
                .antMatchers("/api/newuser").hasAnyRole("user","admin")
                .antMatchers("/api/users/{id}").hasAnyRole("user","admin")
                .antMatchers("/api/users/{id}").hasAnyRole("user","admin")
                .antMatchers("/api/editusers/{id}").hasAnyRole("user","admin")
                .antMatchers("/api/postbycategory/{id}").hasAnyRole("user","admin")
                .antMatchers("/api/deleteusers/{id}").hasAnyRole("user","admin")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();
        http.csrf().disable().cors().configurationSource(request -> corsFilter());
        http.headers().frameOptions().disable();

    }

    @Bean
    public CorsConfiguration corsFilter() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "PUT"));
        config.setAllowedHeaders(List.of("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin"));
        config.addAllowedHeader("Authorization");
        return config;
    }
}
