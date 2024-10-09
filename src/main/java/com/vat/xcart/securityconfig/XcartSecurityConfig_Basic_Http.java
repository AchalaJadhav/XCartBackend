package com.vat.xcart.securityconfig;//package com.vat.xcart.securityconfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
////Spring-boot Basic Http Config
//@Configuration
//public class XcartSecurityConfig_Basic_Http {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF in a more modern way
//                .authorizeHttpRequests(auth -> auth
//                        // No Auth for following api path
//        //                .requestMatchers("/auth/signup", "/auth/login").permitAll() // Allow public access to these endpoints
//                        .requestMatchers("*").permitAll() // Allow public access to these endpoints
//                        .anyRequest().authenticated() // All other requests require authentication
//                )
//                .httpBasic(withDefaults());; // Use HTTP Basic Authentication
//
//        return http.build();
//    }
//}
