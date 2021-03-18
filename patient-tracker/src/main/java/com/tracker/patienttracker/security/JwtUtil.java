package com.tracker.patienttracker.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sun.tools.sjavac.Log;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String secretkey = "${jwt.secret}";

	public String extractUsername(String token) {
		try {
			
			String token1 = token;
			return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token1).getBody().getSubject();
		} 
		catch (Exception e) {
			return null;
		}
	}
	
	public String extractRole(String token) throws Exception {
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretkey.getBytes("UTF-8")).parseClaimsJws(token);
		String role = claims.getBody().get("role", String.class);
                return role;
	}

	public String generateToken(UserDetails userDetails,String role) {
		Log.info(secretkey+" Secret Key");
		final String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
		String compact = Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.claim("role", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))// token for 60 min
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		return compact;
	}
	
	public String generateTokenForReset(UserDetails userDetails,String role) {
		final String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
		String compact = Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.claim("role", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))// token for 3 min
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		return compact;
	}

	
	public Boolean validateToken(String token) {
		try {
			String token1 = token;
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token1).getBody().getExpiration();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public UsernamePasswordAuthenticationToken getAuthenticationToken(String authToken,
			org.springframework.security.core.Authentication authentication, UserDetails userDetails) {
		final JwtParser jwtParser = Jwts.parser().setSigningKey(secretkey);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(authToken);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("role").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}
	
	
}