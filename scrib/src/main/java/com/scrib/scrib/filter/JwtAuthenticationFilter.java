//package com.scrib.scrib.filter;
//
//import com.scrib.scrib.appuser.ApplicationUserService;
//import com.scrib.scrib.utility.JwtProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static com.scrib.scrib.constant.SecurityConstant.JWT_TOKEN_HEADER;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private ApplicationUserService userService;
//
//    @Autowired
//    private JwtProvider jwtProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request
//            , HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String header = request.getHeader(JWT_TOKEN_HEADER);
//        String username = null;
//        String authToken = null;
//
//        if (){
//
//        }
//
//    }
//}
