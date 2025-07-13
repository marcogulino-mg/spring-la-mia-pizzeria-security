package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/pizzas/create", "/pizzas/edit/**", "/pizzas/delete/**", "/pizzas/*/discounts")
                .hasAuthority("ADMIN")
                .requestMatchers("/ingredients/**").hasAuthority("ADMIN")
                .requestMatchers("/discounts/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                .requestMatchers("/pizzas/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/**").permitAll())
                .formLogin(Customizer.withDefaults())
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // INFO: User Service
        authProvider.setUserDetailsService(userDetailService());

        // INFO: Password Encoder
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // EXPLANATION: Delegation of responsibility of the password encoder to the DB
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}