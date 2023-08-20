package com.javatechie.spring.camel.api;

public class DingDto {
	String umnHash;

	public String getUmnHash() {
		return umnHash;
	}

	public void setUmnHash(String umnHash) {
		this.umnHash = umnHash;
	}

	public DingDto(String umnHash) {
		super();
		this.umnHash = umnHash;
	}

	public DingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DingDto [umnHash=" + umnHash + "]";
	}

}
