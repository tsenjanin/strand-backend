package me.strand.mapper;

import me.strand.model.dto.view.mainpage.AnnouncementSummary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    List<AnnouncementSummary> getAnnouncementSummaries();
}
