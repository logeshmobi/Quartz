package io.gomobi.quartz.exception;

import lombok.Getter;

@Getter
public class InvalidRequestData extends RuntimeException {

    private final String reason;

    public InvalidRequestData(String reason,String message) {
        super(message);
        this.reason = reason;
    }

    public InvalidRequestData(String message, Throwable cause, String reason) {
        super(message, cause);
        this.reason = reason;
    }
}
