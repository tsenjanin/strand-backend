package me.strand.model.dto.view.mainpage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementSummary {
    private Integer idAnnouncement;
    private String title;
    private String content;
    private Short type;
    private LocalDateTime tstamp;
}
