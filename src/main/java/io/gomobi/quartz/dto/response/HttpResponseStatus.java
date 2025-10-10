package io.gomobi.quartz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HttpResponseStatus {

    // success response status
    FETCHED(HttpStatus.OK, ApiResponse.Status.SUCCESS),

    // data persisting status
    CURRENCY_PERSISTED(HttpStatus.CREATED, ApiResponse.Status.SUCCESS),

    // request data related failure
    INVALID_BASE_CURRENCY(HttpStatus.BAD_REQUEST, ApiResponse.Status.FAILURE),
    INVALID_TARGET_CURRENCY(HttpStatus.BAD_REQUEST, ApiResponse.Status.FAILURE),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, ApiResponse.Status.FAILURE),

    // api rate limit exceeded failure
    REQUEST_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, ApiResponse.Status.FAILURE),

    // internal issue/error
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ApiResponse.Status.ERROR);

    private final HttpStatus httpStatus;
    private final ApiResponse.Status responseStatus;

}
