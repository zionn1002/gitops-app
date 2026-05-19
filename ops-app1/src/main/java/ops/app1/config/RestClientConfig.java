package ops.app1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${app.ops-app2-url:http://ops-app2:8081}")
    private String opsApp2Url;

    @Bean
    public RestClient opsApp2Client() {
        return RestClient.builder()
                .baseUrl(opsApp2Url)
                .build();
    }
}