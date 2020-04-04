package hu.iac.webshop.auth;

import java.util.Date;

import org.springframework.stereotype.Component;

import hu.iac.webshop.domain.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator  {

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	public String generate(Account account) {
        Claims claims = Jwts.claims().setSubject(account.getUserName());
        claims.put("id", account.getId());
        claims.put("role", account.getRole());

        String token = Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, "iacwebshop").compact();

        System.out.println("TOKEN::..." + token);
        return token;
	}

}
