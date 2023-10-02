package network.service;

import lombok.extern.slf4j.Slf4j;
import network.dto.RegisterModel;
import network.dto.UserModel;
import network.dto.UserRegisterBodyModel;
import network.entity.UserInfo;
import network.repository.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserInfoService(UserInfoRepository userInfoRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserModel getUserInfo(String id) {
        var userInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Анкета не найдена"));
        return new UserModel(userInfo);
    }

    public RegisterModel saveUser(UserRegisterBodyModel body) {
        if (Objects.isNull(body.getFirstName()) || Objects.isNull(body.getSecondName()) || Objects.isNull(body.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");
        var userInfo = userInfoRepository.saveAndFlush(UserInfo.builder()
                .firstName(body.getFirstName())
                .secondName(body.getSecondName())
                .age(body.getAge())
                .birthdate(body.getBirthdate())
                .biography(body.getBiography())
                .city(body.getCity())
                .password(bCryptPasswordEncoder.encode(body.getPassword()))
                .id(UUID.randomUUID().toString())
                .build());
        return new RegisterModel(userInfo.getId());
    }

    public List<UserModel> search(String firstName, String secondName) {
        if (Objects.isNull(firstName) || Objects.isNull(secondName))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");
        return userInfoRepository.findByFirstNameAndSecondName(firstName, secondName).stream().map(UserModel::new).collect(Collectors.toList());
    }
}
