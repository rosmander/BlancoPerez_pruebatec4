package com.example.pruebatec4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/agency/hotels").permitAll()
                        .requestMatchers(HttpMethod.GET, "/agency/rooms").permitAll()
                        .requestMatchers(HttpMethod.GET, "/agency/flights").permitAll()
                        .requestMatchers(HttpMethod.POST, "/agency/room-booking/new").permitAll()
                        .requestMatchers(HttpMethod.POST, "/agency/flight-booking/new").permitAll()
                        .requestMatchers(HttpMethod.POST, "/agency/hotels/new/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/agency/hotels/edit/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/agency/hotels/delete/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/agency/flights/new/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/agency/flights/edit/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/agency/flights/delete/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }
}