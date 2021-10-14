package com.msproject.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

	@GetMapping("/userServiceFallBack")
	public String userServiceFallBackMethod() {
		return "User Service is taking longer than expeceted" + 
			   "Please try again later!";
	}
	
}
