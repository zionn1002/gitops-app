package ops.app1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorTriggerController {

    /**
     * 명시적 에러 트리거 엔드포인트.
     * Spring Boot의 예약 경로 /error와 충돌 회피 위해 /error-trigger 사용.
     */
    @GetMapping("/error-trigger")
    public String triggerError() {
        throw new RuntimeException("Intentional error from /error-trigger");
    }
}