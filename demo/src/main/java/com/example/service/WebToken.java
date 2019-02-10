
package com.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
public class WebToken {

    private final String key = "secret";
    private final byte[] secret = key.getBytes();
    private final Algorithm algorithm = Algorithm.HMAC256(secret);
    private DecodedJWT jwt;
    private static final String AUTHENTICATION_SCHEME = "Bearer ";

    private int tokenExpireTime() {
        String tokenInMinute = "5";
        if (isNullOrEmpty(tokenInMinute)) {
            tokenInMinute = "1";
        }

        int tokenExpireTime = Integer.parseInt(tokenInMinute) * 60 * 1000;
        return tokenExpireTime;
    }

    public String CreateToken(String userId, String password) {
        try {

            JWTCreator.Builder jwtCreator = JWT.create();
            String jsonWebToken = jwtCreator
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withIssuer("Deloyar")
                    .withSubject("Test")
                    .withAudience("Demo Service")
                    .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpireTime()))
                    .withClaim("UserId", userId)
                    .withClaim("Password", password)
                    .sign(algorithm);

            String token = AUTHENTICATION_SCHEME + jsonWebToken;
            return token;
        } catch (JWTCreationException | IllegalArgumentException exp) {
            throw exp;
        }
    }

    private Boolean isNullOrEmpty(String tokenInMinute) {
        return tokenInMinute == null || tokenInMinute.equalsIgnoreCase("");
    }

    public void VerifyToken(String token) throws Exception {

        try {
            if (token.contains(AUTHENTICATION_SCHEME)) {
                int schemeIndex = token.indexOf(AUTHENTICATION_SCHEME);
                if (schemeIndex != 0) {
                    throw new Exception("Invalid token. Please provide a valid token");
                }
            } else {
                throw new Exception("Invalid token. Please provide a valid token");
            }

            Date newExpireTime = new Date(System.currentTimeMillis() + tokenExpireTime());
            jwt = JWT
                    .require(algorithm)
                    .acceptExpiresAt(newExpireTime.getTime())
                    .build()
                    .verify(token.substring(AUTHENTICATION_SCHEME.length()).trim());
        } catch (JWTVerificationException | IllegalArgumentException exp) {
            throw exp;
        }

    }

    public String GetPropertyFromToken(String token, String claimName) throws Exception {
        try {
            VerifyToken(token);
            return jwt.getClaim(claimName).asString();
        } catch (Exception exp) {
            throw exp;
        }
    }

}
