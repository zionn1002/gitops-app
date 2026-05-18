package ops.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiOpsController {
	
	@GetMapping("/ops")
	public String OpsTest() {
		return  "Get - /api/ops";
	}
	
	@GetMapping("/health")
	public String healthCheck() {
		return "OK\n";
	}
}
 