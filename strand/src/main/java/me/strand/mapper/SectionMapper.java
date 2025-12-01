package me.strand.mapper;

import me.strand.model.dto.view.mainpage.SectionSummary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SectionMapper {
    List<SectionSummary> getSectionSummaries();
}
