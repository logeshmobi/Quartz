package io.gomobi.quartz.common.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtil {

    private StringUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Util cannot be instantiated");
    }

    public static String camelToUpperSnake(String input) {
        return input
                .replaceAll("([a-z])([A-Z]+)", "$1_$2")
                .toUpperCase();
    }

    public static String arrayToStringWithDelimiterWithQuote(String delimiter, Object... objects){

        if (objects == null)
            throw new NullPointerException("Null object data received in the Method arrayToString() of " + StringUtil.class);

        return Arrays.stream(objects)
                .map(Object::toString)
                .map(StringUtil::quoteAnObject)
                .collect(Collectors.joining(delimiter));
    }

    public static String quoteAnObject(Object data){
        return "'%s'".formatted(data);
    }
}
