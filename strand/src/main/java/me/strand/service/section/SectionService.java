package me.strand.service.section;

import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.ValidationException;
import me.strand.mapper.SectionMapper;
import me.strand.model.rest.request.InsertSectionRequest;
import me.strand.model.rest.request.UpdateSectionRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final SectionMapper sectionMapper;

    @Transactional
    public void insertSection(InsertSectionRequest request) {
        try {
            sectionMapper.insertSection(request);
        } catch (Exception e) {
            throw new ValidationException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.INSERT_SECTION_FAILED))
            );
        }
    }

    @Transactional
    public void updateSection(UpdateSectionRequest request) {
        try {
            sectionMapper.updateSection(request);
        } catch (Exception e) {
            throw new ValidationException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.UPDATE_SECTION_FAILED))
            );
        }
    }

    @Transactional
    public void deleteSection(Integer idSection) {
        try {
            sectionMapper.deleteSection(idSection);
        } catch (Exception e) {
            throw new ValidationException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.DELETE_SECTION_FAILED))
            );
        }
    }
}
