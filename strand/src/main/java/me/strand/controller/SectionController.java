package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.InsertSectionRequest;
import me.strand.model.rest.request.UpdateSectionRequest;
import me.strand.service.section.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("section")
@RequiredArgsConstructor
@Validated
public class SectionController {
    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<Void> insertSection(@ModelAttribute InsertSectionRequest request) {
        sectionService.insertSection(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateSection(@ModelAttribute UpdateSectionRequest request) {
        sectionService.updateSection(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSection(Integer idSection) {
        sectionService.deleteSection(idSection);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
