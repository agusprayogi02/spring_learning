package io.agus.learning.security;

import io.agus.learning.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AppUserService service;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().headers().disable().authorizeRequests().antMatchers(
                    "/auth/register", "/v3/api-docs/**", "/swagger-ui.html",
                    "/swagger-ui/**").permitAll()
            .anyRequest().fullyAuthenticated()
            .and().httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(service);
    return provider;
  }

}
