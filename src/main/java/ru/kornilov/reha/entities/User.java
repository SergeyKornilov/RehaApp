package ru.kornilov.reha.entities;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;



@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @NotBlank(message = "Username cannot be empty")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Password cannot be empty")
    @Column(name = "full_name")
    private String fullName;

//    @Email(message = "Email is not correct")
//    @NotBlank(message = "Email cannot be empty")
//    private String email;

    @NotBlank(message = "Password cannot be empty")
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}