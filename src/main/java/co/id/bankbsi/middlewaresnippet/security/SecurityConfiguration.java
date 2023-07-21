package co.id.bankbsi.middlewaresnippet.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
public class SecurityConfiguration {

        @Bean
        public UserDetailsManager userDetailsManager(DataSource datasource) {
                JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
                jdbcUserDetailsManager.setUsersByUsernameQuery(
                                "select user_id, pw, active from members where user_id=?");
                jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                                "select user_id, role from roles where user_id=?");

                return jdbcUserDetailsManager;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(configurer -> configurer
                                                .antMatchers("/adminPage/**").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/pages/login")
                                                .loginProcessingUrl("/authenticateTheUser")
                                                .permitAll())
                                .logout(logout -> logout.permitAll())
                                .exceptionHandling(exception -> exception.accessDeniedPage("/accessDenied"));

                return http.build();
        }

}
