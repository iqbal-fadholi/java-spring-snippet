package co.id.bankbsi.middlewaresnippet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider(UserService userService) {
                DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
                auth.setUserDetailsService(userService); // set the custom user details service
                auth.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
                return auth;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(configurer -> configurer
                                                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/showMyLoginPage")
                                                .loginProcessingUrl("/authenticateTheUser")
                                                .permitAll())
                                .logout(logout -> logout.permitAll())
                                .exceptionHandling(exception -> exception.accessDeniedPage("/accessDenied"));

                return http.build();
        }

}
