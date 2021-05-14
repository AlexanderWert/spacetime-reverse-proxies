package co.elastic.apm.onweek.dummyapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class DummyRestController {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    Config config;

    @GetMapping("/api/endpoint")
    public String someEndPoint(@RequestHeader Map<String, String> headers) throws ExecutionException, InterruptedException {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://" + config.getDownstreamServiceUrl() + "/api/second/endpoint", String.class);
        Thread.sleep(200);

        return response.getBody();
    }

    @GetMapping("/api/second/endpoint")
    public String secondEndPoint(@RequestHeader Map<String, String> headers) throws ExecutionException, InterruptedException {
        StringBuilder strBuilder = new StringBuilder();
        headers.forEach((key, value) -> {
            strBuilder.append("<li>");
            strBuilder.append(key);
            strBuilder.append(" : ");
            strBuilder.append(value);
            strBuilder.append("</li>");
        });
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://elastic.co", String.class);
        Thread.sleep(100);

        return "<h1>Hello World!</h1>\n<ul>" + strBuilder.toString() + "</ul>";
    }
}
