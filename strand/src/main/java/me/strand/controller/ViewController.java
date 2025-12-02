package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.model.dto.view.mainpage.PostSummary;
import me.strand.model.dto.view.mainpage.SubsectionSummary;
import me.strand.model.dto.view.mainpage.SubtopicSummary;
import me.strand.model.dto.view.post.PostDetails;
import me.strand.model.rest.request.ContentType;
import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.model.rest.response.MainPageContentResponse;
import me.strand.service.kafka.KafkaProducerService;
import me.strand.service.view.ViewService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
