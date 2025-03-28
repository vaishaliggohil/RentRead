package com.project.rentread.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.rentread.entites.Role;
import com.project.rentread.services.RentalUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RentalUserDetailsService userDetailsService;

    @Value("${password.encryption.strength}")
    private int passwordStrength;

    public SecurityConfig(RentalUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(passwordStrength);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/unauth/**")
                        .permitAll())
                .authorizeHttpRequests(request -> request.requestMatchers("/manage/**")
                        .hasAnyAuthority(Role.ADMIN.name()))
                .authorizeHttpRequests(request -> request.requestMatchers("/book/available")
                        .hasAnyAuthority(Role.ADMIN.name(), Role.USER.name()))
                .authorizeHttpRequests(request -> request.requestMatchers("/book")
                        .hasAnyAuthority(Role.ADMIN.name()))
                .authorizeHttpRequests(request -> request.requestMatchers("/rent/**")
                        .hasAnyAuthority(Role.USER.name()))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}
