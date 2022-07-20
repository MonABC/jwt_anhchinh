package com.example.jwt_anhchinh2007.security.jwt;

import com.example.jwt_anhchinh2007.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "maitienvietdhcn1@gmail.com";
    private int jwtExpiration = 86400;

    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Validate JWT signature -> Message: {}", e);
        } catch (MalformedJwtException e) {
            logger.error("The token invalid format -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT Token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT Token -> Massage: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
