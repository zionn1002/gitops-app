package ops.app2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DataController {

    private static final Logger log = LoggerFactory.getLogger(DataController.class);

    @Value("${app.profile:default}")
    private String profile;

    @GetMapping("/data")
    public Map<String, Object> getData() {
        log.info("GetData called");

        return Map.of(
                "source", "ops-app2",
                "profile", profile,
                "data", List.of(
                        Map.of("id", 1, "name", "item-A"),
                        Map.of("id", 2, "name", "item-B"),
                        Map.of("id", 3, "name", "item-C")
                )
        );
    }
}