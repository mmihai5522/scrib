package com.scrib.scrib.filter;

import com.scrib.scrib.utility.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.scrib.scrib.constant.SecurityConstant.OPTIONS_HTTP_METHOD;
import static com.scrib.scrib.constant.SecurityConstant.TOKEN_PREFIX;
import static org.springframework.http.HttpStatus.*;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;

    public JwtAuthorizationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)){
            response.setStatus(OK.value());
        }else {
            String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)){
                filterChain.doFilter(request,response);
                return;
            }

            String token=authHeader.substring(TOKEN_PREFIX.length());
            String username=jwtProvider.getSubject(token);
            if(jwtProvider.isValidToken(username,token) &&
            SecurityContextHolder.getContext().getAuthentication()== null){
                List<GrantedAuthority> authorities=jwtProvider.getAuthorities(token);
                Authentication authentication=jwtProvider.getAuthentication(username,authorities,request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request,response);
        }
    }
}
