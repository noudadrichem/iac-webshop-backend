package hu.iac.webshop.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import hu.iac.webshop.domain.Account;

@Component
public class JwtValidator {

    private String secret = "iacwebshop";

    public Account validate(String token) {
        Account jwtUser = null;
        try {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

            jwtUser = new Account();
            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
