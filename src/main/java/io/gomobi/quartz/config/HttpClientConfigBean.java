package io.gomobi.quartz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
public class HttpClientConfigBean {

    private Duration  connectionTimeout;

    @Bean
    public HttpClient httpClient(@Value("${http.client.connection-timeout}") Duration connectionTimeout){
        return HttpClient.newBuilder()
                .connectTimeout(connectionTimeout)
                .version(HttpClient.Version.HTTP_2)
                .build();
    }
}
