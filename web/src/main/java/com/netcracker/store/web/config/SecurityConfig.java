package com.netcracker.store.web.config;

import com.netcracker.store.logic.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

/**
 * Created by A-one on 17.04.2017.
 */
@Configuration
@EnableWebSecurity
@Import(ServiceConfig.class)
@PropertySource("classpath:web.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${userRole}")
    private String userRole;

    @Value("${adminRole}")
    private String adminRole;

    @Value("${permitAllAntMatchers}")
    private String[] permitAllAntMatchers;

    @Value("${permitAllGetAntMatchers}")
    private String[] permitAllGetAntMatchers;

    @Value("${permitAllPostAntMatchers}")
    private String[] permitAllPostAntMatchers;

    @Value("${userRoleAntMatchers}")
    private String[] userRoleAntMatchers;

    @Value("${userRoleGetAntMatchers}")
    private String[] userRoleGetAntMatchers;

    @Value("${userRolePostAntMatchers}")
    private String[] userRolePostAntMatchers;

    @Value("${userRolePutAntMatchers}")
    private String[] userRolePutAntMatchers;

    @Value("${ignoringAntMatchers}")
    private String[] ignoringAntMatchers;

    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(permitAllAntMatchers)
                    .permitAll()
                .antMatchers(HttpMethod.GET, permitAllGetAntMatchers)
                    .permitAll()
                .antMatchers(HttpMethod.POST, permitAllPostAntMatchers)
                    .permitAll()
                .antMatchers(userRoleAntMatchers)
                    .hasAuthority(userRole)
                .antMatchers(userRoleGetAntMatchers)
                    .hasAuthority(userRole)
                .antMatchers(userRolePostAntMatchers)
                    .hasAuthority(userRole)
                .antMatchers(userRolePutAntMatchers)
                    .hasAuthority(userRole)
                .anyRequest()
                    .hasAuthority(adminRole)
                .and()
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(ignoringAntMatchers);
    }
}
