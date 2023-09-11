package com.rioinvest.authms.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rioinvest.authms.repository.UserRepository;
import com.rioinvest.authms.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        var tokenJWT = recuperateToken(request);
        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var user = userRepository.findByUsername((String) subject);
            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }

    private String recuperateToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) return authHeader.replace("Bearer ", "");
        return null;
    }
}
