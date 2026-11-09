package com.booking.tourbookingplatform.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("$jwt.secret")
    private String jwtsecret;
    @Value("$jwt.expiration")
    private String jwtexpiration;

    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtsecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(String identifier) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtexpiration);
        return Jwts.builder().subject(identifier).setIssuedAt(now).setExpiration(expiryDate).signWith(getSigningKey()).compact();
    }
    public String getIdentifierFromJwt(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
