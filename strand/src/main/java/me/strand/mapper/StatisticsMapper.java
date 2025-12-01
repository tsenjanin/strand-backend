package me.strand.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsMapper {
    Integer getTotalMembers();
    Integer getTotalPosts();
}
