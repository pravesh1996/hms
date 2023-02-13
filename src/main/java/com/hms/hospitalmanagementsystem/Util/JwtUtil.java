package com.hms.hospitalmanagementsystem.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.val;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Date;

import java.util.function.Function;

@Component
public class JwtUtil {


	@Value("${com.hms.hospitalmanagementsystem.jwt.SECRET_KEY}")
    private String SECRET_KEY;
	@Value("${com.hms.hospitalmanagementsystem.jwt.expirationDateInMs}")
	private String expirationTime;

	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails user) {
        return doGenerateToken(user.getUsername());
    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("role", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		Long expTime = Long.parseLong(expirationTime);
		final Date createdDate = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://devglan.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(createdDate.getTime() + (expTime * 1000)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

	// public Claims getClaimsFromToken(String token) {
	// 	return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	//   }


	// public String refreshToken(String token) {
	// 	Claims claims = getClaimsFromToken(token);
	// 	claims.setIssuedAt(new Date(System.currentTimeMillis()));
	// 	claims.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10));
	// 	return generateToken(claims);
	//   }
}