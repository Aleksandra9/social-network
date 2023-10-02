package network.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import network.dto.RegisterModel;
import network.dto.UserModel;
import network.dto.UserRegisterBodyModel;
import network.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController implements UserApi {
    private final UserInfoService userInfoService;

    public ResponseEntity<UserModel> getUserInfo(@PathVariable("id") String id) {
        return new ResponseEntity<>(userInfoService.getUserInfo(id), HttpStatus.OK);
    }

    public ResponseEntity<RegisterModel> userRegisterPost(@RequestBody UserRegisterBodyModel body) {
        return new ResponseEntity<>(userInfoService.saveUser(body), HttpStatus.OK);
    }

    public ResponseEntity<List<UserModel>> searchUsers(@RequestParam(value = "first_name") String firstName, @RequestParam(value = "last_name") String lastName) {
        return new ResponseEntity<>(userInfoService.search(firstName, lastName), HttpStatus.OK);
    }
}
