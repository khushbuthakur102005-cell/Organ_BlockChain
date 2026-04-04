package com.transplantchain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    private static final String GANACHE_URL = "http://127.0.0.1:7545";

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(GANACHE_URL));
    }
}
