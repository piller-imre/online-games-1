package hu.lev.onlinegames.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lev.onlinegames.persist.TokenDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenDao tokenDao;
    private final String secret = "d7470a8b4fb4360dcefcf455417b04234e3c76f6aecc872bbfb8ba4686483260";


//	@Override
	public String createJWT(String id, long ttlMillis) {

	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .signWith(signatureAlgorithm, signingKey);
	    
	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
//	@Override
	public Claims parseJWT(String jwt) {
	    
		Claims claims = null; 
		try {
			//This line will throw an exception if it is not a signed JWS (as expected)
		    claims = Jwts.parser()
		       .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
		       .parseClaimsJws(jwt).getBody();
		    // System.out.println("Username: " + claims.getId());
		    // System.out.println("Expiration: " + claims.getExpiration());
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Invalid JWT!");
		}
	    
	    return claims;
	}
	
	public int getId(String jwt) {
		Claims claims = parseJWT(jwt);
		int id = 0;
		
		if(claims != null) {
			id = Integer.parseInt(claims.getId());
		}
		
		return id;
	}
}
