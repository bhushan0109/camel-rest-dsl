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
				.setHeader(HttpHeaders.ACCEPT, simple("*/*"))
//Finding Details about 
				.toD("http://localhost:9090/my/10").convertBodyTo(String.class).process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						System.out.println("Output:- " + exchange.getIn().getBody().toString());
					}
				});
	}
}