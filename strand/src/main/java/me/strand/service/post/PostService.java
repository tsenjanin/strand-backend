package me.strand.service.post;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.InsertPostRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    public void insertPost(InsertPostRequest insertPostRequest, Boolean hidden) {
        // TODO
    }
}
