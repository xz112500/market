package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {
    public static final long NOW_TIME=System.currentTimeMillis();
    public static final long VALID_TIME = 1000 * 60 * 60 *24 * 3;
    public static String getJWT(Integer id,String username,String password){
            JwtBuilder jwtBuilder=Jwts.builder()
                .setIssuer(username)
                .setSubject("leyou")
                .setIssuedAt(new Date())
                .setExpiration(new Date(NOW_TIME + VALID_TIME))
                .claim("id", id)
                .claim("username",username)
                .claim("password",password)
                .signWith(SignatureAlgorithm.HS256, "Fine");
            return jwtBuilder.compact();
    }
    public static Claims parseToken(String token){
             return  Jwts.parser()
                    .setSigningKey("Fine")
                    .parseClaimsJws(token)
                    .getBody();
    }
}
