package com.example.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;


@SuppressWarnings("all")
@Component
public class JWTUtil {

    private static final String SECRET = "abcdefghijklmnopqrstuvwxyz1234567890";

    public static String createToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR_OF_DAY, 24);
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build().verify(token);
    }

}