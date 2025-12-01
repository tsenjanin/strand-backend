package me.strand.service.system;

import lombok.RequiredArgsConstructor;
import me.strand.mapper.SystemVariableMapper;
import me.strand.model.dto.systemvariable.SystemVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static me.strand.utils.constants.SystemVariables.*;

@Service
@RequiredArgsConstructor
public class SystemVariableService {
    private final Logger log = LoggerFactory.getLogger(SystemVariableService.class);
    private final SystemVariableMapper systemVariableMapper;

    @Scheduled(fixedRate = 60000L)
    public void updateSystemVariables() {
        var sysVariables = systemVariableMapper.getSystemVariables();

        LLM_MODERATION_ENABLED = Boolean.parseBoolean(
                findVariable(sysVariables, "llm_moderation_enabled")
        );

        log.info("System variables updated at {}. Next update will be at {}",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1)
        );
    }

    private String findVariable(List<SystemVariable> systemVariableList, String variableName) {
        return systemVariableList.stream()
                .filter(systemVariable -> systemVariable.getName().equals(variableName))
                .map(SystemVariable::getValue)
                .findFirst()
                .orElse(null);
    }
}
