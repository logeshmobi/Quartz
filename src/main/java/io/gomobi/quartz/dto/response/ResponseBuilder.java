package io.gomobi.quartz.dto.response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

public final class ResponseBuilder {

    private ResponseBuilder() {}

    public static <T> ResponseEntity<ApiResponse<T>> build(T data, HttpResponseStatus status, HttpServletRequest request){
        return buildResponseEntity(
                data,
                null,
                status,
                request
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>> build(List<ErrorDescription> errors, HttpResponseStatus status, HttpServletRequest request){
        return buildResponseEntity(
                null,
                errors,
                status,
                request
        );
    }

    private static <T> ResponseEntity<ApiResponse<T>> buildResponseEntity(
            T data,
            List<ErrorDescription> errors,
            HttpResponseStatus status,
            HttpServletRequest request){
        ApiResponse.Status responseStatus = status.getResponseStatus();

        return new ResponseEntity<>(
                new ApiResponse<>(
                        responseStatus,
                        responseStatus.getMessage(),
                        data,
                        errors,
                        Instant.now(Clock.systemUTC()),
                        request.getRequestURI()
                ),
                status.getHttpStatus()
        );
    }



}
