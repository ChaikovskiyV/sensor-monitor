package com.example.sensormonitor.security.jwt;

import com.example.sensormonitor.exception.ApplicationNotValidDataException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

import static com.example.sensormonitor.exception.ErrorMessages.NOT_VALID_TOKEN;

@Component
public class JwtTokenProvider {
    public static final Logger logger = LogManager.getLogger();
    private static final String USER_ROLE = "userRole";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final int TOKEN_EXPIRATION_PERIOD = 8 * 60 * 60 * 1000;
    private String tokenSecretKey = "secret key";
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    public JwtTokenProvider(JwtUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void encodeSecretKey() {
        tokenSecretKey = Base64.getEncoder().encodeToString(tokenSecretKey.getBytes());
    }

    public String generateToken(JwtUserDetails userDetails) {
        Date current = new Date();
        Date expirationDate = new Date(current.getTime() + TOKEN_EXPIRATION_PERIOD);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim(USER_ROLE, userDetails.getUserRole().name())
                .setIssuedAt(current)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecretKey)
                .compact();
    }

    public Authentication getAuthenticationByToken(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsernameByToken(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String extractToken(HttpServletRequest servletRequest) {
        String bearerToken = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        return bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)
                ? bearerToken.substring(TOKEN_PREFIX.length()) : null;
    }

    public String getUsernameByToken(String token) {
        return Jwts.parser()
                .setSigningKey(tokenSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(tokenSecretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (SignatureException e) {
            logger.error(() -> String.format(NOT_VALID_TOKEN, token));
            throw new ApplicationNotValidDataException(NOT_VALID_TOKEN, token);
        }
    }
}