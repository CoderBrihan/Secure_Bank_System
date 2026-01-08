package com.example.Banking_System.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
         return httpSecurity
                 .csrf(csrfConfig->csrfConfig.disable())
                 .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authorizeHttpRequests((authorize)->authorize
                                 .requestMatchers("/user/getDetails/**").authenticated()
                                 .requestMatchers("/check/**","/user/**","/auth/**").permitAll()
                                 .requestMatchers("/bank/**").hasRole("ROLL_ADMIN_MAIN")
                                 .requestMatchers("/branch/allUsers/**").hasRole("ROLL_ADMIN")
                                 .requestMatchers("/branch/**").hasRole("ROLL_EMP")
                         )
                 .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    UserDetailsService userDetailsService(){
//        UserDetails user1 = User.withUsername("admin")
//                .password(passwordEncoder.encode("pass"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//    }
}
