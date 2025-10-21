package me.strand.service.system;

import lombok.RequiredArgsConstructor;
import me.strand.mapper.SystemVariableMapper;
import me.strand.model.SystemVariable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemVariableService {
    private final SystemVariableMapper systemVariableMapper;

    public List<SystemVariable> getSystemVariables() {
        return systemVariableMapper.getSystemVariables();
    }
}
