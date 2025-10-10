package io.gomobi.quartz.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ApiResponse<T>(
        Status status,
        String message,
        T data,
        List<ErrorDescription> errorDescriptions,
        Instant timeStamp,
        String path
) {

    @RequiredArgsConstructor
    @Getter
    public enum Status{
        SUCCESS("Request processed successfully"),
        FAILURE("Request failed"),
        ERROR("An error occurred while processing the request"),
        UNKNOWN("Unknown status");

        private final String message;
    }
}
