package me.strand.mapper;

import me.strand.model.dto.view.mainpage.SubsectionSummary;
import me.strand.model.rest.request.InsertSubsectionRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubsectionMapper {
    List<SubsectionSummary> getSubsectionSummariesForSection(Integer idSection);
    void insertSubsection(InsertSubsectionRequest request);
}
