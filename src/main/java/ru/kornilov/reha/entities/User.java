package ru.kornilov.reha.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;



@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @Column(name = "username")
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 25, message = "Username length: min 2 characters, max - 25")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 225, message = "Password length: min 8 characters, max - 225")
    private String password;

    @Column(name = "full_name")
    @NotBlank(message = "Full name cannot be empty")
    @Size(min = 2, max = 50, message = "FullName length: min 8 characters, max - 50")
    private String fullName;

//    @Email(message = "Email is not correct")
//    @NotBlank(message = "Email cannot be empty")
//    private String email;

    @NotBlank(message = "Role cannot be empty")
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