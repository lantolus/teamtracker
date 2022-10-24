package com.example.TeamTracker.Controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.TeamTracker.Exceptions.EmployeeNotFoundException;
import com.example.TeamTracker.Exceptions.MandatoryFieldsNullException;
import com.example.TeamTracker.Model.Role;
import com.example.TeamTracker.Model.User;
import com.example.TeamTracker.Service.User.UserService;
import com.example.TeamTracker.Service.User.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//Setting this class as a REST Controller , so we can call APIs

@RestController
//Setting base path for endpoints related to User objects
@RequestMapping("api/v1/users")
//Allowing FE to interact /w BE through port 3000
@CrossOrigin(origins = "http://localhost:3000/")

public class UserController {

    //Injecting User Service field Param
    private  UserService userService;
    private  UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserService userService,UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }

    //API for Creating new Users,and to save them to the DB
    @RequestMapping(path = "/createNew",method = RequestMethod.POST,produces = {"application/json"})
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new MandatoryFieldsNullException();
        }
    }

    @RequestMapping(path = "/role/save",method = RequestMethod.POST,produces = {"application/json"})
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        try {
            userService.saveRole(role);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new MandatoryFieldsNullException();
        }
    }

    @RequestMapping(path = "/role/addToUser",method = RequestMethod.POST,produces = {"application/json"})
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        try {
            userService.addRoleToUser(form.getUsername(), form.getRoleName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new MandatoryFieldsNullException();
        }
    }



    //API for retrieving all User objects from DB
    @RequestMapping(path = "/findAll",produces = {"application/json"},method = RequestMethod.GET)
    public Collection<User> getAllUsers() {
        return userService.getUsers();
    }

    //API for deleting User objects based on ID attribute
    @RequestMapping (path = "/deleteById/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
    }


    /*
    API for generating a new Access token based on Access token , after checking expiration date
    if refresh token is still valid then as a response it will send a renewed access token,and refresh token
     */
    @RequestMapping(path = "/refreshToken",produces = {"application/json"},method = RequestMethod.GET)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(("Start123").getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + (System.currentTimeMillis() + 10 * 60 * 100)))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("Roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                response.setContentType("application/json;charset=utf-8");
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("username", username);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus( FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());  //sends 403 error

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }

    }



}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}