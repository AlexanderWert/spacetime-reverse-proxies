package co.elastic.apm.onweek.dummyapp;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config")
@EnableConfigurationProperties
@Data
public class Config {
    String downstreamServiceUrl;
}
