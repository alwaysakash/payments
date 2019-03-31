package com.monese.payments.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class JWTConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${monese.oauth.tokenTimeout:360}")
    private int expiration;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    private String signinKey;

    @Autowired
    private TokenStore tokenStore;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("m0n#S#");
        return converter;
    }

    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List listAccessTokenConverter = Arrays.asList(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(listAccessTokenConverter);

        endpointsConfigurer.tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(enhancerChain);
        endpointsConfigurer.authenticationManager(authenticationManager);
        endpointsConfigurer.userDetailsService(userDetailsService);


    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("monese").secret("secret").accessTokenValiditySeconds(expiration)
                .scopes("read", "write").authorizedGrantTypes("password", "refresh_token").resourceIds("resource");
    }


}
