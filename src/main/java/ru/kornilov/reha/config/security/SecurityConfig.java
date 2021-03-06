package ru.kornilov.reha.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.kornilov.reha.exceptions.SecurityConfigureException;
import ru.kornilov.reha.service.interfaces.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws SecurityConfigureException {

        try {
            auth.userDetailsService(loginService)
                    .passwordEncoder(NoOpPasswordEncoder.getInstance());
        } catch (Exception e) {
            throw new SecurityConfigureException(e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws SecurityConfigureException {

        try {
            http.authorizeRequests()
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/patient-list/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
                    .antMatchers("/my-patient-list/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
                    .antMatchers("/patient/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
                    .antMatchers("/add-patient-page/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
                    .antMatchers("/prescribing/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
                    .antMatchers("/event-list/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_NURSE') or hasRole('ROLE_DOCTOR')")
                    .antMatchers("/main/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_NURSE') or hasRole('ROLE_DOCTOR')")
                    .antMatchers("/profile/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_NURSE') or hasRole('ROLE_DOCTOR')")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(myAuthenticationSuccessHandler())
                    .permitAll()
                    .failureUrl("/login-error")
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll()
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access-denied");
        } catch (Exception e) {
            throw new SecurityConfigureException(e);
        }
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new CustomUrlAuthenticationSuccessHandler();
    }

}