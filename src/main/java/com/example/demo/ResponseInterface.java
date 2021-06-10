package com.example.demo;

public interface ResponseInterface {

	public String directCall();

	public String getBackendResponseFallback(RuntimeException e);

	public String inDirectCall();

}
