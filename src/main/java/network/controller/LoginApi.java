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
import network.dto.ErrorModel;
import network.dto.LoginBodyModel;
import network.dto.TokenModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/login")
@OpenAPIDefinition(info = @Info(title = "Highload: social network (login)", version = "1.0"))
public interface LoginApi {
    @Operation(description = "Упрощенный процесс аутентификации путем передачи идентификатор пользователя и получения токена для дальнейшего прохождения авторизации")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная аутентификация", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenModel.class))),
            @ApiResponse(responseCode = "400", description = "Невалидные данные"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<TokenModel> login(@Parameter(in = ParameterIn.DEFAULT, schema = @Schema()) @RequestBody LoginBodyModel body);
}
