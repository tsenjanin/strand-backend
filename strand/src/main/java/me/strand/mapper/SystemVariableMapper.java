package me.strand.mapper;

import me.strand.model.dto.systemvariable.SystemVariable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemVariableMapper {
    List<SystemVariable> getSystemVariables();
}
