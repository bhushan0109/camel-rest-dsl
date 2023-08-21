package com.javatechie.spring.camel.api;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class NumberApiRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class).process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("Handling Exception..");
				System.out.println(exchange.getException().getCause());
			}
		}).handled(true);
//Start of Route
		from("direct: UmnHashApiRoute").log("UmnHashApiRoute..")
//Setting Up the Headers and Method
				.setHeader(Exchange.HTTP_METHOD, simple("GET"))
				.setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
				.setHeader(HttpHeaders.ACCEPT, simple("*/*")).setHeader("x-wu-externalRefId", constant("123"))
				.setHeader("x-wu-correlationId", constant("b8a7cbbb-76d3-446d-83e9-daffe2d9cf88"))
				.setHeader("x-wu-accountNumber", constant("930934207"))
				.setHeader("Cookie", constant("JSESSIONID-B6BBCCF95D1B6484747A19F83368793A"))
				.setHeader("x-wu-countrycode", constant("US"))
//Finding Details about 
				.toD("https://digital-chs-uat.digitalprod.awswuintranet.net/cusprofile/v2/cust/customers/umnhash")
				//.toD("https://dummy.restapiexample.com/api/v1/employees")
				
				.convertBodyTo(String.class).process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						System.out.println("Output:- " + exchange.getIn().getBody().toString());
					}
				});
	}

	// local test not used
//	@Override
//	public void configure() throws Exception {
//		onException(Exception.class).process(new Processor() {
//			@Override
//			public void process(Exchange exchange) throws Exception {
//				System.out.println("Handling Exception..");
//				System.out.println(exchange.getException().getCause());
//			}
//		}).handled(true);
////Start of Route
//		from("direct: UmnHashApiRoute").log("UmnHashApiRoute..")
////Setting Up the Headers and Method
//				.setHeader(Exchange.HTTP_METHOD, simple("GET"))
//				.setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
//				.setHeader(HttpHeaders.ACCEPT, simple("*/*"))
////Finding Details about 
//				.toD("http://localhost:9090/my/10").convertBodyTo(String.class).process(new Processor() {
//					@Override
//					public void process(Exchange exchange) throws Exception {
//						System.out.println("Output:- " + exchange.getIn().getBody().toString());
//					}
//				});
//	}

}