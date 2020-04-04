package hu.iac.webshop.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import hu.iac.webshop.domain.Account;


@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken userToken) throws AuthenticationException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) userToken;
        String stringToken = token.getToken();

        Account account = validator.validate(stringToken);

        if (account == null) {
            throw new RuntimeException("Invalid JWT");
        }

        List<GrantedAuthority> grantedAuth = AuthorityUtils.commaSeparatedStringToAuthorityList(account.getRole());
        return new JwtUserDetails(account.getUserName(), account.getId(), stringToken, grantedAuth);
    }
}
