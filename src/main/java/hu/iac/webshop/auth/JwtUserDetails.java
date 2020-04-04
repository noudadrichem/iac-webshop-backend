package hu.iac.webshop.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L; // default stuff?
    private String username;
    private Long id;
    private String token;
    private List<GrantedAuthority> grantedAuth;

    public JwtUserDetails(String username, Long id, String token, List<GrantedAuthority> grantedAuth) {
        this.username = username;
        this.id = id;
        this.token = token;
        this.grantedAuth = grantedAuth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuth;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
