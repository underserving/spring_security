package com.security.securityApplicaton.filter;

import com.security.securityApplicaton.services.JwtService;
import com.security.securityApplicaton.services.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     String header=request.getHeader("Authorization");
     String token=null;
     String userName=null;
     if(header!=null && header.startsWith("Bearer")){
         token=header.substring(7);
         userName= jwtService.extractUserDetails(token);
//         System.out.println(userName);
     }
     if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userdetails=myUserDetailsService.loadUserByUsername(userName);
        if(jwtService.validateToken(token,userdetails)){
            UsernamePasswordAuthenticationToken authToken=
                    new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
     }
     filterChain.doFilter(request,response);
    }
}
