package ironhack.banking_system.banking_system.security;

import ironhack.banking_system.banking_system.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                //.mvcMatchers(HttpMethod.GET, "/users").hasAnyRole("USER")
                //.mvcMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/accountHolder/account/{accountHolderId}").hasAnyRole("USER")
                .mvcMatchers(HttpMethod.GET, "/balance/accountHolder/{accountNumber}").hasAnyRole("USER")
                .mvcMatchers(HttpMethod.POST, "/balance/transfer").hasAnyRole("USER")
                .mvcMatchers(HttpMethod.PATCH,"/balance/add").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/account/add").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/balance/accountNumber/{accountNumber}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/account/status").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/account/delete/{accountNumber}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/balance/decrease").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/thirdparty/add").hasRole("ADMIN")

                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }
}
