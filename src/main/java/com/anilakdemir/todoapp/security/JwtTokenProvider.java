package com.anilakdemir.todoapp.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author anilakdemir
 * @date 18 Aralık 2021 Cumartesi
 * @time 18:03
 */
@Component
public class JwtTokenProvider {

    @Value("${todoapp.app.secret}")
    private String APP_SECRET;

    @Value("${todoapp.expires.in}")
    private Long EXPIRES_IN;

    public String generateJwtToken(Authentication authentication){

        Map<String,Object> map = new HashMap();
        map.put("roles","deneme");
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime()+ EXPIRES_IN);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                //.setClaims(map)
                .claim("roles",userDetails.getAuthorities())
                .compact();
    }

    //TO GET USER ID
    Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token){
        //PARSE EDILIYORSA BIZIM OLUSTURDUGUMUZ BIR TOKENDIR ANLAMINA GELIR.
        try{
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return !isTokenExpired(token);
        }catch ( SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException exception){
            return false;
        }

    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
