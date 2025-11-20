package me.strand.mapper;

import me.strand.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByUsername(String username);
}
