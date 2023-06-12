//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 09.05.2023
// 


package ch.es.pl.quotes.api.configuration;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.equals("/api/items")) {
            chain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token introuvable ou incorrect");
            return;
        }
        String token = authHeader.substring(7);
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            request.setAttribute("email",email);
            int id = claims.get("id",Integer.class);
            request.setAttribute("id",id);
            chain.doFilter(request, response);
        } catch ( JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token introuvable ou incorrect");
        }

    }

}
