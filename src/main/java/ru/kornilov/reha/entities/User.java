package ru.kornilov.reha.entities;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @Column(name = "email")
    @NotBlank(message = "Email cannot be empty")
    @Email
    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "activationCode")
    private String activationCode;

    @Column(name = "imgName")
    private String imgName;

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
        return enabled;
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

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}