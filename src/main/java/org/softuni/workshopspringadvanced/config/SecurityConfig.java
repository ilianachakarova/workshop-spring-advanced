package org.softuni.workshopspringadvanced.config;

import org.softuni.workshopspringadvanced.users.OAuth2UserAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    public final UserDetailsService userDetailsService;
    private final OAuth2UserAuthSuccessHandler successHandler;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, OAuth2UserAuthSuccessHandler successHandler, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                antMatchers("/login**", "/registration**").permitAll().
                antMatchers("/**").
                authenticated().
                and().
                formLogin().
                loginPage("/login").
                loginProcessingUrl("/login/authenticate").
                failureForwardUrl("/login?param.error=bad_credentials").
                successForwardUrl("/home").
                and().
                logout().
                logoutUrl("/logout").
                logoutSuccessUrl("/login").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .successHandler(successHandler);//TODO

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }
}
