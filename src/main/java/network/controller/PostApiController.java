package network.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import network.dto.PostCreateBodyModel;
import network.dto.PostModel;
import network.dto.PostUpdateBodyModel;
import network.service.PostService;
import network.service.UserFeedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
public class PostApiController implements PostApi {
    private final PostService postService;
    private final UserFeedService userFeedService;

    public PostApiController(PostService postService, UserFeedService userFeedService) {
        this.postService = postService;
        this.userFeedService = userFeedService;
    }

    public ResponseEntity<String> postCreatePost(@Parameter(in = ParameterIn.DEFAULT, schema = @Schema()) @Valid @RequestBody PostCreateBodyModel body, Principal principal) {
        return new ResponseEntity<>(postService.create(principal.getName(), body).getId(), HttpStatus.OK);
    }

    public ResponseEntity<Void> postDeleteIdPut(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema()) @PathVariable("id") String id, Principal principal) {
        postService.delete(principal.getName(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<PostModel>> postFeedGet(@DecimalMin("0") @Parameter(in = ParameterIn.QUERY, schema = @Schema(defaultValue = "0")) @Valid @RequestParam(value = "offset", required = false, defaultValue = "0") BigDecimal offset, @DecimalMin("1") @Parameter(in = ParameterIn.QUERY, schema = @Schema(defaultValue = "10")) @Valid @RequestParam(value = "limit", required = false, defaultValue = "10") BigDecimal limit, Principal principal) {
        return new ResponseEntity<>(userFeedService.get(principal.getName(), limit), HttpStatus.OK);
    }

    public ResponseEntity<PostModel> postGetIdGet(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema()) @PathVariable("id") String id) {
        return new ResponseEntity<>(new PostModel(postService.getId(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postUpdatePut(@Parameter(in = ParameterIn.DEFAULT, schema = @Schema()) @Valid @RequestBody PostUpdateBodyModel body, Principal principal) {
        postService.update(principal.getName(), body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
