package io.gomobi.quartz.exception;

import io.gomobi.quartz.dto.response.ApiResponse;
import io.gomobi.quartz.dto.response.ErrorDescription;
import io.gomobi.quartz.dto.response.HttpResponseStatus;
import io.gomobi.quartz.dto.response.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiResponse<Object>> methodArgumentExceptions(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        return ResponseBuilder.build(
                List.of(new ErrorDescription(e.getName(), "Received invalid value in the request %s".formatted(extractInvalidValue(e)))),
                HttpResponseStatus.INVALID_REQUEST,
                request
        );
    }


    private String extractInvalidValue(MethodArgumentTypeMismatchException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof ConversionFailedException conversionFailedException) {
            Object value = conversionFailedException.getValue();
            return value != null ? value.toString() : "null";
        }
        return ex.getValue() != null ? ex.getValue().toString() : "null";
    }
}
