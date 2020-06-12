package com.glsid.medapp.config;

import com.glsid.medapp.modele.Personne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private Long id;
    private String cin;
    private String password;
    private String fullName;
    private List<GrantedAuthority> grantedAuthorities;

    public MyUserDetails() {
    }

    public MyUserDetails(Personne personne) {
        id = personne.getId();
        cin = personne.getCin();
        password = personne.getPassword();
        fullName = personne.getNom() + " " + personne.getPrenom();
        grantedAuthorities = Arrays.stream(personne.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return cin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
