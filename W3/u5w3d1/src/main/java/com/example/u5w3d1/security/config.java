package com.example.u5w3d1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
// Serve per dichiarare i permessi di accesso sui singoli endpoints
public class config {
    @Autowired
    private JWTFilter jwtFilter;

    @Bean
        // Bean che mi serve per configurare a piacimento la security filter chain
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disabilitare alcuni comportamenti di default
        httpSecurity.formLogin(AbstractHttpConfigurer::disable); // Non vogliamo il form di login
        httpSecurity.csrf(AbstractHttpConfigurer::disable); // Non vogliamo la protezione da CSRF
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Non vogliamo le sessioni

        // Aggiungere filtri custom
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Aggiungere/rimuovere determinate regole di protezione per gli endpoint
        // Qui possiamo determinare se debba essere necessaria un'autenticazione per accedervi
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder getBCrypt() {
        return new BCryptPasswordEncoder(11);

    }
}
