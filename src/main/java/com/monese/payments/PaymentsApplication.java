package com.monese.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableResourceServer
@EnableTransactionManagement
public class PaymentsApplication extends ResourceServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/payments/v1/transactions/**")
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer oauthServer) {
        oauthServer
                .resourceId("resource");
    }
}
