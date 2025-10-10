package io.gomobi.quartz.common.constant;

public enum CurrencyCode {

    // --- Major Global ---
    USD,
    EUR,
    GBP,
    JPY,
    CHF,
    CAD,
    AUD,
    NZD,

    // --- Asia ---
    INR,
    CNY,
    HKD,
    SGD,
    KRW,
    THB,
    MYR,
    IDR,
    PHP,
    TWD,
    VND,

    // --- Middle East / Africa ---
    AED,
    SAR,
    QAR,
    KWD,
    BHD,
    ZAR,
    EGP,

    // --- Americas ---
    MXN,
    BRL,
    ARS,
    CLP,
    COP,
    PEN,

    // --- Europe (Other) ---
    SEK,
    NOK,
    DKK,
    PLN,
    CZK,
    HUF,
    RON,

    // --- Others ---
    TRY,
    RUB,
    UAH,
    ISK;

    public static boolean contains(String value){
        for (CurrencyCode currencyCode: CurrencyCode.values())
            return currencyCode.name().equals(value);

        return false;
    }


}

