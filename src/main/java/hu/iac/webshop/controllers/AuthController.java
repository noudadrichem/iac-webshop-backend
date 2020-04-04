package hu.iac.webshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.iac.webshop.auth.JwtTokenGenerator;
import hu.iac.webshop.domain.Account;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/generate")
    public ResponseEntity generateToken(@RequestBody Account account) {
        JwtTokenGenerator tokenGenerator = new JwtTokenGenerator();
        String token = tokenGenerator.generate(account);

        if (token != null) {
            return new ResponseEntity<String>(token, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
