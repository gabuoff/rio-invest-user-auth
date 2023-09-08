package com.rioinvest.authms.domain;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Document(collection = "users")
public class User implements UserDetails{

    @Id
    private String id;
    private String username, password, email, cpf, fullName;
    
    private Date dateOfBirth, lastLogin;
    private int failedLoginAttempts;
    private boolean locked, active;

    @DBRef(lazy = true)
    private LoginAudit audit;

   // Define roles and authorities for the user
    private Set<String> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert user roles to Spring Security GrantedAuthority objects
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implement account expiration logic if needed
        return true; // Assuming no account expiration logic for now
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement credentials expiration logic if needed
        return true; // Assuming no credentials expiration logic for now
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}