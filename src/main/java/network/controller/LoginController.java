package network.controller;

import lombok.AllArgsConstructor;
import network.dto.LoginBodyModel;
import network.dto.TokenModel;
import network.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController implements LoginApi {
    private final LoginService loginService;

    public ResponseEntity<TokenModel> login(LoginBodyModel body) {
        return new ResponseEntity<>(loginService.checkUser(body), HttpStatus.OK);
    }
}
