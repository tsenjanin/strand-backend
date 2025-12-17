package me.strand.service.subsection;

import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.ValidationException;
import me.strand.mapper.SubsectionMapper;
import me.strand.model.rest.request.InsertSubsectionRequest;
import me.strand.model.rest.request.UpdateSubsectionRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubsectionService {
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final SubsectionMapper subsectionMapper;

    @Transactional
    public void insertSubsection(InsertSubsectionRequest request) {
        try {
            subsectionMapper.insertSubsection(request);
        } catch (Exception e) {
            throw new ValidationException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.INSERT_SUBSECTION_FAILED))
            );
        }
    }

    @Transactional
    public void updateSubsection(UpdateSubsectionRequest request) {
        try {
            subsectionMapper.updateSubsection(request);
        } catch (Exception e) {
            throw new ValidationException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.UPDATE_SUBSECTION_FAILED))
            );
        }
    }

    @Transactional
    public void deleteSubsection(Integer idSubsection) {
        try {
            subsectionMapper.deleteSubsection(idSubsection);
        } catch (Exception e) {
            throw new ValidationException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.DELETE_SUBSECTION_FAILED))
            );
        }
    }

    @Transactional
    public void hideSubsection(Integer idSection, Boolean isHidden) {
        subsectionMapper.hideSubsection(idSection, isHidden);
    }

    @Transactional
    public void lockSubsection(Integer idSection, Boolean isLocked) {
        subsectionMapper.lockSubsection(idSection, isLocked);
    }
}
