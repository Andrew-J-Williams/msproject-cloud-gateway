package com.msproject.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
	
	
	@GetMapping("/createUserServiceFallBack")
	public String createUserServiceFallBackMethod() {
		return "The server for the User Service has encountered an error in the creation process. " + 
			   "Please try again later!";
	}

	@GetMapping("/userServiceFallBack")
	public String userServiceFallBackMethod() {
		return "User Service is taking longer than expeceted. " + 
			   "Please try again later!";
	}
	
	@GetMapping("/createDepartmentServiceFallBack")
	public String createDepartmentServiceFallBackMethod() {
		return "The server for the Department Service has encountered an error in the creation process. " + 
			   "Please try again later!";
	}
	
	@GetMapping("/departmentServiceFallBack")
	public String departmentServiceFallBackMethod() {
		return "Department Service is taking longer than expeceted. " + 
			   "Please try again later!";
	}
	
	
}
