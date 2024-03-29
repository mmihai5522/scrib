package com.scrib.scrib.utility;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.scrib.scrib.appuser.ApplicationUserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.scrib.scrib.constant.SecurityConstant.*;
import static java.util.Arrays.stream;

@Component
public class JwtProvider {

    @Value("jwt.secret")
    private String secret;

    public String generateJwtToken(ApplicationUserPrincipal userPrincipal){
        String[] claims=getClaimsFromUser(userPrincipal);
        return JWT.create().withIssuer(GET_ARRAYS_TOKEN)
                .withAudience(GET_ARRAYS_ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(AUTHORITIES,claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));

    }

    public List<GrantedAuthority> getAuthorities(String token){
        String[] claims=getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities
                                        , HttpServletRequest request){

        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(username,null,authorities);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            return authenticationToken;
    }

    public boolean isValidToken(String username, String token){
        JWTVerifier verifier=getJwtVerifier();

        return StringUtils.isNotEmpty(username) && !isExpiredToken(verifier,token);

    }

    public String getSubject(String token){
        JWTVerifier verifier=getJwtVerifier();

        return verifier.verify(token).getSubject();
    }

    private boolean isExpiredToken(JWTVerifier verifier, String token) {
        Date expiration= verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier jwtVerifier= getJwtVerifier();
        return jwtVerifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJwtVerifier() {

        JWTVerifier verifier;
        try {

            Algorithm algorithm=Algorithm.HMAC512(secret);
            verifier=JWT.require(algorithm).withIssuer(GET_ARRAYS_TOKEN).build();

        }catch (JWTVerificationException e){

            throw new JWTVerificationException(INVALID_TOKEN);
        }
        return verifier;

    }

    private String[] getClaimsFromUser(ApplicationUserPrincipal userPrincipal) {
        List<String> authorities=new ArrayList<>();
        for (GrantedAuthority granted: userPrincipal.getAuthorities()) {
            authorities.add(granted.getAuthority());
        }
            return authorities.toArray(new String[0]);
    }


}
