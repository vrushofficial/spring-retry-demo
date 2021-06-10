package com.example.demo;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ResponseImpl implements ResponseInterface {

	private static int retryCount = 0;

	@Override
	@Retryable(value = { MyCustomException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public String directCall() {

		System.out.println("Retry Count:" + ++retryCount);

		throw new MyCustomException("Will fallback to MyCustomException on completion of all retries");
	}

	/*
	 * trying to create directCall()'s dynamic proxy - but self invocation is not
	 * possible. so, will call directCall() with 'this' reference only.
	 */
	@Override
	public String inDirectCall() {
		return directCall();
	}

	@Override
	@Recover
	public String getBackendResponseFallback(RuntimeException e) {
		retryCount = 0;
		System.out.println("Reached to fallback call");
		return "Reached to fallback call";
	}
}
