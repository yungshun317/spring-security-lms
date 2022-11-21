package yungshun.chang.springsecuritylms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class LMSSecurityConfig {

    /* In-Memory Authentication
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // Add users for in memory authentication
        UserDetails user1 = User.withUsername("yungshun")
                .password(encoder.encode("secret"))
                .roles("ADMIN", "STAFF", "USER")
                .build();
        UserDetails user2 = User.withUsername("Mai Shiranui")
                .password(encoder.encode("secret"))
                .roles("STAFF", "USER")
                .build();
        UserDetails user3 = User.withUsername("Lili Rochefort")
                .password(encoder.encode("secret"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
     */

    // JDBC Authentication
    @Bean
    public UserDetailsManager users(DataSource securityDataSource) {
        return new JdbcUserDetailsManager(securityDataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /* Default login page
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/").hasRole("USER")
                .anyRequest().denyAll()
        ).formLogin(withDefaults());
         */

        // Custom login page
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/").hasRole("USER")
                        .requestMatchers("/staff/**").hasRole("STAFF")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().denyAll()
        ).formLogin(form -> form.loginPage("/plain-login").loginProcessingUrl("/authenticateTheUser").permitAll())
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

        return http.build();
    }

}
