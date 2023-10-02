package network.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import network.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class FriendApiController implements FriendApi {
    private final FriendService friendService;

    public ResponseEntity<Void> friendDeleteUserIdPut(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema()) @PathVariable("user_id") String userId, Principal principal) {
        friendService.deleteFriend(principal.getName(), userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> friendSetUserIdPut(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema()) @PathVariable("user_id") String userId, Principal principal) {
        friendService.setFriend(principal.getName(), userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
