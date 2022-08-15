package com.yxc.seabirdmall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class SeabirdmallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeabirdmallThirdPartyApplication.class, args);
    }

}
