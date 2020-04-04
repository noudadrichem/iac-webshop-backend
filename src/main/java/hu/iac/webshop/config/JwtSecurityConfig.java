package hu.iac.webshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hu.iac.webshop.auth.JwtAuthenticationEntryPoint;
import hu.iac.webshop.auth.JwtAuthenticationProvider;
import hu.iac.webshop.auth.JwtAuthenticationTokenFilter;
import hu.iac.webshop.auth.JwtSuccessHandler;

import java.util.Collections;

import javax.servlet.Filter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider authProvider;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authManager() {
        return new ProviderManager(
            Collections.singletonList(authProvider)
        );
    }

    @Bean
    public JwtAuthenticationTokenFilter authTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter("/authed/**");
        filter.setAuthenticationManager(authManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());

        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/authed/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore((Filter) authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

}
