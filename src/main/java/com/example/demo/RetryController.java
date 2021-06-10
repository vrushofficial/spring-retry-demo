package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {

	@Autowired
	ResponseInterface responseInterface;

	/*
	 * ResponseInterface is autowired here. as directCall() is annotated with
	 * @Retryable, spring can create directCall()'s proxy from here
	 */
	@GetMapping("/retryCall")
	public String callCompatibleRetry() {
		return responseInterface.directCall();
	}

	/*
	 * proxy will not be created from here beacause inDirectCall() not
	 * marked @Retryable
	 */
	@GetMapping("/simpleCall")
	public String callIncompatibleRetry() {
		return responseInterface.inDirectCall();
	}

}
