package io.gomobi.quartz.api;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.common.constant.RequestSource;
import io.gomobi.quartz.dto.data.GlobalExchangeRateDto;
import io.gomobi.quartz.dto.response.ApiResponse;
import io.gomobi.quartz.dto.response.HttpResponseStatus;
import io.gomobi.quartz.dto.response.ResponseBuilder;
import io.gomobi.quartz.service.ExchangeRateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/rates")
public class InternalServiceExchangeRateController {

    private final ExchangeRateService merchantExchangeRateService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Object>> getLatestExchangeRates(
        @RequestParam
        CurrencyCode from,

        @RequestParam
        List<CurrencyCode> to,

        @RequestParam(required = false, defaultValue = "1")
        BigDecimal amount,

        @RequestParam
        @NotEmpty(message = "Master MID cannot be null or Empty")
        String id,

        @RequestParam
        RequestSource source,

        HttpServletRequest servletRequest
    ){
        GlobalExchangeRateDto exchangeRateData = merchantExchangeRateService.findExchangeRate(
                from,
                to,
                amount,
                id
        );
        return ResponseBuilder.build(exchangeRateData, HttpResponseStatus.FETCHED, servletRequest);
    }
}
