package com.example.TeamTracker.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Creating request with params
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is: {}", username);
        log.info("Password is: {}", password);
        //Authenticating received response
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    @JsonSerialize
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(("Start123").getBytes()); //Setting the Algorithm type
        String access_token = JWT.create()  //Creating Access Token
                .withSubject(user.getUsername()) // Setting Username as Subject
                .withExpiresAt(new Date(System.currentTimeMillis() + (System.currentTimeMillis() + 10 * 60 * 100))) // Setting Access token expiration to 1 Min
                .withIssuer(request.getRequestURL().toString()) //Setting Issuer to be the URL
                .withClaim("Roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))//Setting claims to be the ROLE
                .sign(algorithm); //Signing token with Algorithm
        String refresh_token = JWT.create()//Creating Refresh token
                .withSubject(user.getUsername())// Setting Username as Subject
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // Setting Access token expiration to 10 Min
                .withIssuer(request.getRequestURL().toString())//Setting Issuer to be the URL
                .sign(algorithm); //Signing token with Algorithm
        Map<String, String> tokens = new HashMap<>();//Creating a hashmap of Tokens
        tokens.put("access_token", access_token);//Appending acc token to Tokens hashmap
        tokens.put("refresh_token", refresh_token);//Appending refresh token to Tokens Hashmap
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
