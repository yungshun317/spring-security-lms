package yungshun.chang.springsecuritylms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class LMSSecurityConfig {

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

}
