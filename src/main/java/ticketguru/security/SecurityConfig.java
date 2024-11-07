package ticketguru.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ticketguru.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests((requests) -> requests

         // Event: vain admin voi säätää eventtejä, mutta katsoa saavat useritkin
         .requestMatchers(HttpMethod.GET, "/api/event/**").hasAnyRole("ADMIN", "USER")
         .requestMatchers(HttpMethod.POST, "/api/event/**").hasRole("ADMIN")
         .requestMatchers(HttpMethod.PUT, "/api/event/**").hasRole("ADMIN")
         .requestMatchers(HttpMethod.DELETE, "/api/event/**").hasRole("ADMIN")
 
         // Ticket: myös tavallisten käyttäjien pitää päästä katsomaan ja tarkistamaan lippuja
         .requestMatchers("/api/tickets/**").hasAnyRole("ADMIN", "USER")
 
         // AppUser: vain admin pääsee käyttäjiin käsiksi
         .requestMatchers("/api/users/**").hasRole("ADMIN")
 
         // Sale: muut kuin poisto OK myös usereille
         .requestMatchers(HttpMethod.GET, "/api/sales/**").hasAnyRole("ADMIN", "USER")
         .requestMatchers(HttpMethod.POST, "/api/sales").hasAnyRole("ADMIN", "USER")
         .requestMatchers(HttpMethod.PUT, "/api/sales/**").hasAnyRole("ADMIN", "USER")
         .requestMatchers(HttpMethod.DELETE, "/api/sales/**").hasRole("ADMIN")
 
         // TicketType: vain admineille
         .requestMatchers("/api/ticket-types/**").hasRole("ADMIN")
 
         // EventTicketType: GET OK myös usereille, että voi tutkia mahdollisia lipputyyppejä tapahtumassa
         .requestMatchers(HttpMethod.GET, "/api/eventTicketTypes/**").hasAnyRole("ADMIN", "USER")
         .requestMatchers(HttpMethod.POST, "/api/eventTicketTypes").hasRole("ADMIN")
         .requestMatchers(HttpMethod.PUT, "/api/eventTicketTypes/**").hasRole("ADMIN")
         .requestMatchers(HttpMethod.DELETE, "/api/eventTicketTypes/**").hasRole("ADMIN")

        .anyRequest().authenticated()
        )
        .httpBasic(httpBasic -> {
            httpBasic.realmName("TicketGuru");
        })
        .csrf(AbstractHttpConfigurer::disable);
                return http.build();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration
                    .setAllowedOrigins(Arrays.asList("https://ticket-guru-scrum-ritarit-ticketguru.2.rahtiapp.fi"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}