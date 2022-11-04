package yungshun.chang.springsecuritylms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class LMSSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // Add users for in memory authentication
        UserDetails user1 = User.withUsername("yungshun")
                .password(encoder.encode("secret"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("Mai Shiranui")
                .password(encoder.encode("secret"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
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
                .anyRequest().denyAll()
        ).formLogin(form -> form.loginPage("/plain-login").loginProcessingUrl("/authenticateTheUser").permitAll())
                .logout(out -> out.permitAll());

        return http.build();
    }

}
