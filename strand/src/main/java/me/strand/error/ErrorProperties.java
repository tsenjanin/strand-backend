package me.strand.error;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ErrorProperties {
    private final MessageSource messageSource;

    public ErrorProperties() {
        var rrbms = new ReloadableResourceBundleMessageSource();
        rrbms.setDefaultEncoding("UTF-8");
        rrbms.addBasenames("error");
        this.messageSource = rrbms;
    }

    private String getProperty(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    private String getCode(String key) {
        return getProperty(key + ".code");
    }

    private String getMessage(String key) {
        return getProperty(key + ".message");
    }

    private HttpStatus getHttpCode(String key) {
        var httpCodeString = getProperty(key + ".status");
        var httpCodeInt = httpCodeString.isEmpty() ? 500 : Integer.parseInt(httpCodeString);
        return HttpStatus.valueOf(httpCodeInt);
    }

    public Error getError(ErrorCode key) {
        var errorCodeString = key.getCode();
        return new Error(getCode(errorCodeString), getMessage(key.getCode()), getHttpCode(key.getCode()));
    }
}
