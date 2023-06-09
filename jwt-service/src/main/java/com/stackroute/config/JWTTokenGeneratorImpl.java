package com.stackroute.config;

import com.stackroute.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * This class is implementing the JWTTokenGenerator interface. This class has to be annotated with
 * @Service annotation.
 * @Service indicates annotated class is a service
 * which hold business logic in the Service layer
 *
 * */
@Service
public class JWTTokenGeneratorImpl implements JWTTokenGenerator {


    @Value("${jwt.secret}")
    private String secret;

    @Value("${app.jwttoken.message}")
    private String message;

    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken = "";
        /*
         * Generate JWT token and store in String jwtToken
         */
        jwtToken = Jwts.builder().setSubject(user.getId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,secret).compact();
        Map<String,String> jwtTokenMap=new HashMap<>();
        jwtTokenMap.put("token",jwtToken);
        jwtTokenMap.put("message",message);
        return jwtTokenMap;
    }
}
