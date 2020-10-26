package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtRequestFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity
@Configuration
//@Order(1)
public class WebConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(WebConfig.class);

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtRequestFilter jwtRequestFilter;

    public WebConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                     JwtRequestFilter jwtRequestFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //ldif file, need to remove first entry and disable application properties
        auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups")
                .contextSource().ldif("classpath:test-server-2.ldif")
                .root("dc=springframework,dc=org").and().passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder()).passwordAttribute("userPassword")
                .and();

        //url
//        auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups")
//                .contextSource().
//                url("ldap://localhost:8389/dc=springframework,dc=org").and().passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder()).passwordAttribute("userPassword");

//        auth.inMemoryAuthentication().withUser("thomas").password("password").roles("USER").and()
//                .withUser("joe").password("password").roles("USER");

        LOGGER.info("Security configuration loaded.");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().headers().frameOptions().deny().and().authorizeRequests()
                .antMatchers("/auth/login").permitAll().
                antMatchers("/**").authenticated().and().
                exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
