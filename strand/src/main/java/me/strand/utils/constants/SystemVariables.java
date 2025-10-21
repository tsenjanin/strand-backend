package me.strand.utils.constants;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.strand.model.SystemVariable;
import me.strand.service.system.SystemVariableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SystemVariables {
    Logger log = LoggerFactory.getLogger(SystemVariables.class);

    private final SystemVariableService systemVariableService;

    public Integer USER_POST_LIMIT;
    public Integer POST_LIMIT_WINDOW_MIN;
    public Integer LLM_RATE_LIMIT_COUNT;
    public Integer LLM_RATE_LIMIT_WINDOW_MIN;

    @PostConstruct
    private void init() {
        updateSystemVariables();
    }

    @Scheduled(fixedRate = 60000L)
    private void updateSystemVariables() {
        var sysVariables = systemVariableService.getSystemVariables();

        USER_POST_LIMIT = Integer.parseInt(findVariable(sysVariables, "USER_POST_LIMIT"));
        POST_LIMIT_WINDOW_MIN = Integer.parseInt(findVariable(sysVariables, "POST_LIMIT_WINDOW_MIN"));
        LLM_RATE_LIMIT_COUNT = Integer.parseInt(findVariable(sysVariables, "LLM_RATE_LIMIT_COUNT"));
        LLM_RATE_LIMIT_WINDOW_MIN = Integer.parseInt(findVariable(sysVariables, "LLM_RATE_LIMIT_WINDOW_MIN"));

        log.info("System variables updated at {}. Next update will be at {}",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1)
        );
    }

    private String findVariable(List<SystemVariable> systemVariableList, String variableName) {
        return systemVariableList.
                stream()
                .filter(systemVariable -> systemVariable.getName().equals(variableName))
                .map(SystemVariable::getValue)
                .findFirst()
                .orElse(null);
    }
}
