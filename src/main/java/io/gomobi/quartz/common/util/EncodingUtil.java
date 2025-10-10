package io.gomobi.quartz.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.gomobi.quartz.common.constant.Delimiter.COLON;

public class EncodingUtil {
    private EncodingUtil(){}

    public static String basicAuthEncoding(String username, String password){
        return Base64.getEncoder().encodeToString((username + COLON + password).getBytes(StandardCharsets.UTF_8));
    }
}
