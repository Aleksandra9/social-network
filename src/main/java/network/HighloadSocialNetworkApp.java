package network;

import network.configuration.RsaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties(RsaProperties.class)
@EnableCaching
public class HighloadSocialNetworkApp {
    public static void main(String[] args) {
        SpringApplication.run(HighloadSocialNetworkApp.class, args);
    }
}
