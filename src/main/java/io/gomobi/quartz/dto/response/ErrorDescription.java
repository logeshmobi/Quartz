package io.gomobi.quartz.dto.response;

public record ErrorDescription(
        String cause,
        String reason
) {
}
