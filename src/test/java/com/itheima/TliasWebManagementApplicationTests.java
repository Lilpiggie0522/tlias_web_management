package com.itheima;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TliasWebManagementApplicationTests {
    @Test
    public void testUuid() {
        for (int i = 0; i < 1000; i ++) {
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
        }
    }

    @Test
    public void testGenJwt() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", "feizhu");
        hashMap.put("age", 22);
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "piggie")
                .setClaims(hashMap)
                .setExpiration(new Date(System.currentTimeMillis()))
                .compact();
        System.out.println(jwt);
    }

    /*@Test
    public void testParseJwt() {
        Claims body = Jwts.parser()
                .setSigningKey("piggie")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImZlaXpodSIsImV4cCI6MTcwNjUwOTkyNSwiYWdlIjoyMn0.KY3t6e1YSgjynxQQZ75DNgzrgvI-ACzmk_0dSuAOP6Q")
                .getBody();
        System.out.println(body);
    }*/

}