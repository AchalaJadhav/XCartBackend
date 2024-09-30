package com.innovation.xcartbackend.configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.innovation.xcartbackend.util.Role;

import io.jsonwebtoken.*;

/**
 * This class provides the secret key for cryptography of username, and is responsible to track and validate the token.
 * @author Ajinkya.Deshpande
 *
 */
@Component
public class JwtTokenHelper {


	private String secret = "jwtTokenKey";

//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
//		
//	}
//	
//	public Date getExpirationDateFromToken(String token) {
//		return getClaimFromToken(token, Claims::getExpiration);
//	}
//
//	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = getAllClaimsFromToken(token);
//		return claimsResolver.apply(claims);
//	}
//	
//	private Claims getAllClaimsFromToken(String token) {
//		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//	}

//	private Boolean isTokenExpired(String token) {
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}

//	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		return doGenerateToken(claims,userDetails.getUsername());
//		
//	}
//
//	private String doGenerateToken(Map<String, Object> claims, String subject) {
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512,secret).compact();
//	}

//public Boolean validateToken(String token, UserDetails userDetails) {
//	final String username = getUsernameFromToken(token);
//	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}

///===============================================java techie=================
	/**
	 * The claim in spring boot jwt is encoded as the object which was used in the JWS (JSON web signature) payload and this method extracts that claim
	 * @param token
	 * @return claim
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
//	public int extractId(String token) {
//		extractAllClaims(token);
//		
//	}

	/**
	 * This method extracts the expiration date.
	 * @param token
	 * @return Date
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String username, String role) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<String, Object> claims = new HashMap<>();
//		claims.put("role", role);
		System.out.println(auth.getAuthorities());
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public Boolean validateToken(String token) {
		final String username = extractUsername(token);
		return (username.equals(!isTokenExpired(token)));
	}
}
