
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PersonneRepository personneRepository;
    
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.formLogin().loginPage("/login").successHandler(authenticationSuccessHandler).permitAll().and().logout().permitAll();
    	
    	http.authorizeRequests()
    			.antMatchers("/img/**","/css/**","/js/**","/webfonts/**","/webjars/**","/h2-console/**").permitAll();
    	
    	// not yet specified user connect
        http.authorizeRequests()
                .antMatchers("/","/patient/detail","/patient/detail/**","/patient/detail/{\\\\d+}","/patient/**/edit",
                		"/patient/{\\\\d+}/edit","/patient/{\\\\d+}/update")
                .hasAnyRole("PATIENT","SECRETAIRE");

    	http.authorizeRequests()
                .antMatchers("/","/patient/**","/rdv/**","/consult/**")
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


