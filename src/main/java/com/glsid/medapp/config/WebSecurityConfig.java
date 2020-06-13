package com.glsid.medapp.config;

import com.glsid.medapp.dao.PersonneRepository;
import com.glsid.medapp.modele.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PersonneRepository personneRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
    	
    	http.authorizeRequests()
    			.antMatchers("/img/**","/css/**","/webjars/**","/h2-console/**").permitAll();
    	
        http.authorizeRequests()
                .antMatchers( "/patient/detail", "/patient/detail/**")
                .hasRole("PATIENT");
        
        http.authorizeRequests()
                .antMatchers("/rdv/**", "/patient/**", "/patient/detail/**", "/consult/**")
                .hasRole("SECRETAIRE");
        
        http.authorizeRequests()
                .antMatchers("/**")
                .hasRole("ADMIN");
               

        http.authorizeRequests().anyRequest().authenticated();
        
        http.csrf().disable();
        
        http.headers().frameOptions().disable();
        
        http.exceptionHandling().accessDeniedPage("/consult/erreur");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                Personne p = personneRepository.findByCin(s);
                if (p == null) throw new UsernameNotFoundException("CIN n'existe pas");
                return new MyUserDetails(p);
            }
        });

    }


}


