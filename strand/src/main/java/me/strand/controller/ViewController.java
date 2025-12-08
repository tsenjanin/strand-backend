package me.strand.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.strand.model.dto.view.mainpage.PostSummary;
import me.strand.model.dto.view.mainpage.SubtopicSummary;
import me.strand.model.dto.view.post.PostDetails;
import me.strand.model.llm.enums.ContentType;
import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.model.rest.request.InsertPostRequest;
import me.strand.model.rest.response.MainPageContentResponse;
import me.strand.service.auth.CustomUserDetails;
import me.strand.service.kafka.KafkaProducerService;
import me.strand.service.view.ViewService;
import me.strand.validation.annotation.ValidComment;
import me.strand.validation.annotation.ValidPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static me.strand.utils.constants.SystemVariables.KAFKA_TOPIC;

@RestController
@RequestMapping("view")
@RequiredArgsConstructor
@Validated
public class ViewController {
    private final ViewService viewService;
    private final KafkaProducerService kafkaProducerService;

    // NOTE: insert operations for section, subsection and subtopic are not yet implemented, except mappers.

    @GetMapping("main-page")
    public ResponseEntity<MainPageContentResponse> getMainPageContent() {
        var content = viewService.getMainPageContent();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @GetMapping("subsection-details")
    public ResponseEntity<List<SubtopicSummary>> getSubsectionDetails(Integer idSubsection) {
        var content = viewService.getSubsectionDetails(idSubsection);

        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @GetMapping("subtopic-posts")
    public ResponseEntity<List<PostSummary>> getSubtopicPosts(Integer idSubtopic) {
        var content = viewService.getSubtopicPosts(idSubtopic);

        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @GetMapping("post-details")
    public ResponseEntity<PostDetails> getPostDetails(Integer idPost) {
        var content = viewService.getPostDetails(idPost);

        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @PostMapping("insert-post")
    public ResponseEntity<Void> insertPost(@ModelAttribute @ValidPost InsertPostRequest insertPostRequest,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           HttpServletRequest request) {
        kafkaProducerService.queueContent(KAFKA_TOPIC, ContentType.POST, insertPostRequest);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("insert-comment")
    public ResponseEntity<Void> insertComment(@ModelAttribute @ValidComment InsertCommentRequest insertCommentRequest,
                                              @AuthenticationPrincipal CustomUserDetails userDetails,
                                              HttpServletRequest request) {
        kafkaProducerService.queueContent(KAFKA_TOPIC, ContentType.COMMENT, insertCommentRequest);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
