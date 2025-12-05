package me.strand.mapper;

import me.strand.model.dto.view.mainpage.SubtopicSummary;
import me.strand.model.rest.request.InsertSubtopicRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubtopicMapper {
    List<SubtopicSummary> getSubtopicsForSubsection(Integer idSubsection);
    void insertSubtopic(InsertSubtopicRequest request);
}
