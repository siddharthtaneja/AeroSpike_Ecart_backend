package com.aerospike.aerospikecaching.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource ds;

    @Autowired
    public void globalSecurityConfig(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication().dataSource(ds)
                .usersByUsernameQuery("select email,password,active from users where email = ?")
                .authoritiesByUsernameQuery("select email, authentication from users where email = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/users/signup").permitAll()
                .antMatchers("/api/par/*").permitAll()
                .antMatchers("/api/notes").permitAll()
                .antMatchers("/users/getuser").permitAll()
                .antMatchers("/sign/get").permitAll()
                .antMatchers("/users/logout").permitAll()
                .antMatchers("/users/getPass/*").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
        http.cors();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
