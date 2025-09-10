package com.saturday.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * Generate a JWT token
     * Using HS256 algorithm with a secret key
     *
     * @param secretKey secretKey the secret key used for signing
     * @param ttlMillis token expiration time in milliseconds
     * @param claims    custom claims to include in the payload
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // Use HS256 as the signature algorithm
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // Set expiration time
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // Build the JWT
        JwtBuilder builder = Jwts.builder()
                // Add custom claims (payload)
                .setClaims(claims)
                // Sign with algorithm and secret key
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // Set expiration
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * Parse and validate a JWT token
     *
     * @param secretKey secretKey the secret key used to verify the signature
     * @param token     the JWT string
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        // Parse the JWT and extract claims
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
