package com.matija.infobip.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
 
	
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username ,password,enabled from user where username=?")
                        .authoritiesByUsernameQuery("select username, authority from role where username=?");
    }
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/console/**").permitAll()
         
        .and()
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/account").permitAll()
        .antMatchers(HttpMethod.POST, "/register/**").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/statistics/**").hasAnyRole("USER","ADMIN")
        .antMatchers(HttpMethod.GET, "/accounts/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/actuator/**").hasRole("ADMIN")
        .and();
    	httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

 
}