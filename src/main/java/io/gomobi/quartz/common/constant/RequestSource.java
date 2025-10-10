package io.gomobi.quartz.common.constant;

public enum RequestSource {
    PAYIN,
    PAYOUT,
    PORTAL,
    RECKON,
    UNKNOWN,
    MERCHANT;

    public static boolean contains(String value){
        for (RequestSource source:RequestSource.values())
            return source.name().equals(value);

        return false;
    }
}
