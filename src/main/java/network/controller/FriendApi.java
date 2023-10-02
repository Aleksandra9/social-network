package network.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import network.dto.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@RequestMapping("/friend")
@OpenAPIDefinition(info = @Info(title = "Highload: social network (friend)", version = "1.0"))
public interface FriendApi {

    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удалил из друзей пользователя"),
            @ApiResponse(responseCode = "400", description = "Невалидные данные ввода"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/delete/{user_id}", produces = {"application/json"}, method = RequestMethod.PUT)
    ResponseEntity<Void> friendDeleteUserIdPut(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema()) @PathVariable("user_id") String userId, Principal principal);


    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно указал своего друга"),
            @ApiResponse(responseCode = "400", description = "Невалидные данные ввода"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/set/{user_id}", produces = {"application/json"}, method = RequestMethod.PUT)
    ResponseEntity<Void> friendSetUserIdPut(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema()) @PathVariable("user_id") String userId, Principal principal);

}

