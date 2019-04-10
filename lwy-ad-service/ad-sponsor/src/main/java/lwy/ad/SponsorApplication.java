package lwy.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.net.ServerSocket;

@EnableFeignClients  // 这个微服务可调用其他微服务
@EnableCircuitBreaker //断路器，为了实现监控
@EnableEurekaClient  //能够从注册中心中拿取到其他微服务信息
@SpringBootApplication
public class SponsorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SponsorApplication.class,args);
    }
}
