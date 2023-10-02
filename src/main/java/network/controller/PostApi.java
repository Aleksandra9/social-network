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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import network.dto.ErrorModel;
import network.dto.PostCreateBodyModel;
import network.dto.PostModel;
import network.dto.PostUpdateBodyModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RequestMapping("/post")
@OpenAPIDefinition(info = @Info(title = "Highload: social network (post)", version = "1.0"))
public interface PostApi {
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно создан пост", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Невалидные данные ввода"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/create", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<String> postCreatePost(@Parameter(in = ParameterIn.DEFAULT, schema = @Schema()) @Valid @RequestBody PostCreateBodyModel body, Principal principal);


    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно удален пост"),
            @ApiResponse(responseCode = "400", description = "Невалидные данные ввода"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/delete/{id}", produces = {"application/json"}, method = RequestMethod.PUT)
    ResponseEntity<Void> postDeleteIdPut(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema()) @PathVariable("id") String id, Principal principal);


    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получены посты друзей", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PostModel.class)))),
            @ApiResponse(responseCode = "400", description = "Невалидные данные ввода"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/feed", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<PostModel>> postFeedGet(@DecimalMin("0") @Parameter(in = ParameterIn.QUERY, schema = @Schema(defaultValue = "0")) @Valid @RequestParam(value = "offset", required = false, defaultValue = "0") BigDecimal offset, @DecimalMin("1") @Parameter(in = ParameterIn.QUERY, schema = @Schema(defaultValue = "10")) @Valid @RequestParam(value = "limit", required = false, defaultValue = "10") BigDecimal limit, Principal principal);


    @Operation()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен пост", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostModel.class))),
            @ApiResponse(responseCode = "400", description = "Невалидные данные ввода"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/get/{id}", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<PostModel> postGetIdGet(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema()) @PathVariable("id") String id);


    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно изменен пост"),
            @ApiResponse(responseCode = "400", description = "Невалидные данные ввода"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "503", description = "Ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class)))})
    @RequestMapping(value = "/update", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.PUT)
    ResponseEntity<Void> postUpdatePut(@Parameter(in = ParameterIn.DEFAULT, schema = @Schema()) @Valid @RequestBody PostUpdateBodyModel body, Principal principal);

}

