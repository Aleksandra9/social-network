package network.service;

import network.dto.LoginBodyModel;
import network.dto.TokenModel;
import network.repository.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService {
    private final TokenService tokenService;
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(TokenService tokenService, UserInfoRepository userInfoRepository) {
        this.tokenService = tokenService;
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public TokenModel checkUser(LoginBodyModel body) {
        var userInfo = userInfoRepository.findById(body.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        if (bCryptPasswordEncoder.matches(body.getPassword(), userInfo.getPassword()))
            return tokenService.generateAccessToken(userInfo.getId());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");
    }
}
