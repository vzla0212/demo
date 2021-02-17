package com.example.demo;

import com.example.demo.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void init() {
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @EnableWebSecurity
    @Configuration
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/user").permitAll()
                    .antMatchers(HttpMethod.GET, "/demoValidation/calc/").permitAll()
                    .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                    .antMatchers("/swagger-resources/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v2/api-docs").permitAll()
                    .antMatchers("/actuator/**").permitAll()
                    .anyRequest().authenticated();
        }
    }

}
