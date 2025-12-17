package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.InsertSubsectionRequest;
import me.strand.model.rest.request.UpdateSubsectionRequest;
import me.strand.service.subsection.SubsectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subsection")
@RequiredArgsConstructor
@Validated
public class SubsectionController {
    private final SubsectionService subsectionService;

    @PostMapping
    public ResponseEntity<Void> insertSection(@ModelAttribute InsertSubsectionRequest request) {
        subsectionService.insertSubsection(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateSection(@ModelAttribute UpdateSubsectionRequest request) {
        subsectionService.updateSubsection(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSection(Integer idSubsection) {
        subsectionService.deleteSubsection(idSubsection);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("lock")
    public ResponseEntity<Void> lockSubsection(Integer idSubsection, Boolean isLocked) {
        subsectionService.lockSubsection(idSubsection,  isLocked);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("hide")
    public ResponseEntity<Void> hideSubsection(Integer idSubsection, Boolean isHidden) {
        subsectionService.hideSubsection(idSubsection, isHidden);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
