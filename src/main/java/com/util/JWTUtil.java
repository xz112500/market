package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {
    public static final long NOW_TIME=System.currentTimeMillis();
    public static final long VALID_TIME = 1000 * 60 * 60 *24 * 15;
    public String getJWT(String username,String password){
            JwtBuilder jwtBuilder=Jwts.builder()
                .setIssuer(username)
                .setSubject("alibaba")
                .setIssuedAt(new Date())
                .setExpiration(new Date(NOW_TIME + VALID_TIME))
                .claim("id", password)
                .signWith(SignatureAlgorithm.HS256, "Fine");
            return jwtBuilder.compact();
    }
    public String parseToken(String token){

            Claims claims = Jwts.parser()
                    .setSigningKey("Fine")
                    .parseClaimsJws(token)
                    .getBody();
            return claims.toString();
    }
}
