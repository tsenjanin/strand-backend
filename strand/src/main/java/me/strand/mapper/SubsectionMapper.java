package me.strand.mapper;

import me.strand.model.dto.view.mainpage.SubsectionSummary;
import me.strand.model.rest.request.InsertSubsectionRequest;
import me.strand.model.rest.request.UpdateSubsectionRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubsectionMapper {
    List<SubsectionSummary> getSubsectionSummariesForSection(Integer idSection);
    void insertSubsection(InsertSubsectionRequest request);
    void updateSubsection(UpdateSubsectionRequest request);
    void deleteSubsection(Integer idSubsection);
    void hideSubsection(Integer idSubsection, Boolean isHidden);
    void lockSubsection(Integer idSubsection, Boolean isLocked);
}
