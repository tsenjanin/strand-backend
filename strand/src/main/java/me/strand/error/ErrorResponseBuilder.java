package me.strand.error;

import me.strand.utils.constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ErrorResponseBuilder {
    public Map<String, Object> build(Error error) {
        var errorMap = new LinkedHashMap<String, Object>();
        errorMap.put(Constants.ERROR_RESPONSE_TIMESTAMP_KEY, LocalDateTime.now());

        var httpStatus = getHttpStatus(error);
        errorMap.put(Constants.ERROR_RESPONSE_STATUS_CODE_KEY, httpStatus.value());
        errorMap.put(Constants.ERROR_RESPONSE_ERROR_KEY, error.getMessage());

        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return errorMap;
        }

        errorMap.put(Constants.ERROR_RESPONSE_REQUEST_URI_KEY, attributes.getRequest().getRequestURI());
        errorMap.put(Constants.ERROR_RESPONSE_METHOD_KEY, attributes.getRequest().getMethod());

        return errorMap;
    }

    private HttpStatus getHttpStatus(Error error) {
        var httpStatus = error.getHttpStatus();
        if (httpStatus == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return httpStatus;
    }
}
