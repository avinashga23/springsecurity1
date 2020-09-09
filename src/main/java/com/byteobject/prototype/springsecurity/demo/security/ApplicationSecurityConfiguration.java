package com.byteobject.prototype.springsecurity.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import static com.byteobject.prototype.springsecurity.demo.security.ApplicationUserRoles.ADMIN;
import static com.byteobject.prototype.springsecurity.demo.security.ApplicationUserRoles.USER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRedisHttpSession
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .and()
                .httpBasic()
                .and().csrf().disable();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("user1").password(passwordEncoder().encode("user1"))
                .authorities(USER.getAuthorities()).build();
        UserDetails user2 = User.builder().username("user2").password(passwordEncoder().encode("user2"))
                .authorities(USER.getAuthorities()).build();
        UserDetails user3 = User.builder().username("user3").password(passwordEncoder().encode("user3"))
                .authorities(USER.getAuthorities()).build();
        UserDetails user4 = User.builder().username("user4").password(passwordEncoder().encode("user4"))
                .authorities(USER.getAuthorities()).build();
        UserDetails user5 = User.builder().username("user5").password(passwordEncoder().encode("user5"))
                .authorities(USER.getAuthorities()).build();

        UserDetails admin1 = User.builder().username("admin1").password(passwordEncoder().encode("admin1"))
                .authorities(ADMIN.getAuthorities()).build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4, user5, admin1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
