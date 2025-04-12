package com.example.rentease.config;

// config/SecurityConfig.java

import com.example.rentease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity // Enable method-level security
    public class SecurityConfig {

        @Autowired
        private UserRepository userRepository;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return username -> {
                com.example.rentease.model.User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
                return User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole()) // Or use .authorities(user.getAuthorities()) if you have a separate authority field
                        .build();
            };
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity in this example
                    .authorizeHttpRequests((requests) -> requests
                            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() // Allow access to H2 console
                            .requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll() // Allow access to auth endpoints
                            .anyRequest().authenticated()
                    )
                    .headers((headers) -> headers.frameOptions().disable()) // Needed for H2 console
                    .formLogin((form) -> form
                            .permitAll() // Allow all users to access the login page
                    )
                    .logout((logout) -> logout.permitAll()); // Allow all users to access the logout page

            return http.build();
        }
    }