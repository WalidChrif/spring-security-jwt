package com.spring.spring_security.services;

import com.spring.spring_security.entity.TheUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {

//    String stringKey = "";
    SecretKey secretKey;

    public JWTService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            secretKey = keyGenerator.generateKey();
//            stringKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(TheUser user) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuer(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 30)))
                .and()
                .signWith(getKey())
                .compact();
    }

    public SecretKey getKey() {
//        byte[] bytes = Decoders.BASE64.decode(stringKey);
//        byte[] bytes = stringKey.getBytes();
        byte[] bytes = secretKey.getEncoded();
        return Keys.hmacShaKeyFor(bytes);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject );
    }
    private  <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token){
        return extractExpDate(token).after(new Date());
    }
    private Date extractExpDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }
}
