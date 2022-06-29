package com.scrib.scrib.appuser;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrib.scrib.role.ApplicationRoleRepository;
import com.scrib.scrib.role.ApplicationUserRole;
import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;
    private final ApplicationRoleRepository applicationRoleRepository;

    @GetMapping("/users")
    public ResponseEntity<List<ApplicationUser>> getUsers(){
        return ResponseEntity.ok().body(applicationUserService.getUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<List<ApplicationUser>> getUser(String username){
        applicationUserService.loadUserByUsername(username);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/user/save")
    public ResponseEntity<ApplicationUser> saveUserApplication(
            @RequestBody ApplicationUser applicationUser){

        URI uri=URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/user/save").toUriString());

       return ResponseEntity.created(uri).body(applicationUserService.saveUser(applicationUser));
    }

    @PostMapping("/role/save")
    public ResponseEntity<ApplicationUserRole> saveUserApplicationRole(
            @RequestBody ApplicationUserRole applicationUserRole){

        URI uri=URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/role/save").toUriString());

        return ResponseEntity.created(uri).body(
                applicationRoleRepository.save(applicationUserRole));
    }

    @PostMapping("/r2u")
    public ResponseEntity<ApplicationUserRole> saveUserApplicationRole(
            @RequestBody String applicationUser
            ,@RequestBody String applicationUserRole){

        applicationUserService.addRoleToUser(applicationUser,applicationUserRole);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                ApplicationUser user = applicationUserService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getApplicationUserRole().stream().map(ApplicationUserRole::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
