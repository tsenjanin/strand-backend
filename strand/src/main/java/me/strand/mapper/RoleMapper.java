package me.strand.mapper;

import me.strand.model.dto.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> getRolesByGroupId(Integer groupId);
}
