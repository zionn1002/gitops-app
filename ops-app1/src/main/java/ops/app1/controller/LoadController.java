package ops.app1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Random;

@RestController
public class LoadController {

    private final Random random = new Random();

    /**
     * 부하 시뮬레이션 엔드포인트.
     * Day 2-03 (Prometheus + 골든 시그널)에서 활용.
     *
     * type=slow   응답 시간 폭증 (Latency)
     * type=error  500 에러 발생 (Errors)
     * type=cpu    CPU 부하 (Saturation)
     */
    @GetMapping("/load")
    public String load(@RequestParam(defaultValue = "normal") String type) {
        switch (type) {
            case "slow":
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "slow response (3s)";

            case "error":
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "intentional error for testing");

            case "cpu":
                long start = System.currentTimeMillis();
                long sum = 0;
                while (System.currentTimeMillis() - start < 2000) {
                    sum += random.nextLong();
                }
                return "cpu load done, sum=" + sum;

            default:
                return "normal response";
        }
    }
}