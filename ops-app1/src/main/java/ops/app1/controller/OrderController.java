package ops.app1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final RestClient opsApp2Client;

    public OrderController(RestClient opsApp2Client) {
        this.opsApp2Client = opsApp2Client;
    }

    @GetMapping("/orders")
    public Map<String, Object> getOrders() {
        log.info("Calling ops-app2 /api/data");

        Map<String, Object> downstream = opsApp2Client.get()
										                .uri("/api/data")
										                .retrieve()
										                .body(Map.class);

        log.info("Got response from ops-app2: {}", downstream);

        return Map.of(
                "source", "ops-app1",
                "orders", java.util.List.of("order-1", "order-2", "order-3"),
                "downstream", downstream
        );
    }
}