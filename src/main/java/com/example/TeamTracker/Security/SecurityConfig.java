package com.example.TeamTracker.Security;


import com.example.TeamTracker.Filter.CustomAuthenticationFilter;
import com.example.TeamTracker.Filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
        @Override
        protected void configure (HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) //A session will always be created if one doesn"t already exist *was STATELESS before*
                    .and().csrf().disable();
//                  http.authorizeRequests().antMatchers("/**").permitAll();
                    http.authorizeRequests().antMatchers("/login/").permitAll();
                    http.authorizeRequests().antMatchers("/api/v1/employees/findAll").hasAuthority("admin");
                    http.authorizeRequests().antMatchers("/api/v1/employees/createNew/").hasAuthority("admin");
                    http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
                    http.formLogin()
                            .loginProcessingUrl("/login");
                    http.logout()
                            .permitAll()
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID");
                    http.logout().logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));
                    http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}



