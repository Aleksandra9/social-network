package network.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import network.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/user")
@OpenAPIDefinition(info = @Info(title = "Highload: social network (user)", version = "1.0"))
public interface UserApi {
    @Operation(description = "Получение анкеты пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение анкеты пользователя", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class))),
            @ApiResponse(responseCode = "400", description = "Невалидные данные"),
            @ApiResponse(responseCode = "404", description = "Анкета не найдена"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/get/{id}", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<UserModel> getUserInfo(@Parameter(in = ParameterIn.PATH, description = "Идентификатор пользователя", required = true, schema = @Schema()) @PathVariable("id") String id);

    @Operation(description = "Регистрация нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная регистрация", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenModel.class))),
            @ApiResponse(responseCode = "400", description = "Невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/register", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<RegisterModel> userRegisterPost(@Parameter(in = ParameterIn.DEFAULT) @RequestBody UserRegisterBodyModel body);

    @Operation(description = "Поиск анкет")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешные поиск пользователя", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserModel.class)))),
            @ApiResponse(responseCode = "400", description = "Невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/search", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<UserModel>> searchUsers(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Условие поиска по имени", required = true, schema = @Schema()) @Valid @RequestParam(value = "first_name") String firstName, @NotNull @Parameter(in = ParameterIn.QUERY, description = "Условие поиска по фамилии", required = true, schema = @Schema()) @Valid @RequestParam(value = "last_name") String lastName);
}
