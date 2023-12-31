package com.javatechie.spring.camel.api;

import java.io.IOException;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Controller {

	@Autowired
	private ProducerTemplate producerTemplate;

	@Autowired
	private CamelContext camelContext;

	@GetMapping("/umnHash")
	public DingDto umnHash() throws JsonParseException, JsonMappingException, IOException {
		DingDto dingDto = new DingDto();
		Exchange reqExchange = ExchangeBuilder.anExchange(camelContext).build();
		Exchange exchange = producerTemplate.send("direct: UmnHashApiRoute", reqExchange);

		Object body = exchange.getIn().getBody();
		dingDto = new ObjectMapper().readValue(body.toString(), DingDto.class);
		String umnHash = dingDto.getUmnHash();
		System.out.println("final umnHash output ==>" + umnHash);
		System.out.println("DONE!!");
		return dingDto;
	}

	// not used
//	@GetMapping(value = "/my/{id}")
//	@ResponseStatus(value = HttpStatus.OK)
//	public DingDto myString(@PathVariable int id) {
//		DingDto dingDto = new DingDto();
//		if (id == 10) {
//			dingDto.setUmnHash("jghjksfdgsdgfkjasdgfvjadgjcvhjchadjcbhahcjad");
//		} else {
//			dingDto.setUmnHash("id not correct");
//		}
//		return dingDto;
//	}
//	@GetMapping("/number/{id}")
//	public DingDto number(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
//		DingDto dingDto = new DingDto();
//		Exchange exchange = producerTemplate.send("http://localhost:9090/my/" + id, new Processor() {
//			public void process(Exchange exchange) throws Exception {
//			}
//		});
//
//		Message out = exchange.getOut();
//		int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
//		System.out.println("Response: " + String.valueOf(responseCode));
//		if (responseCode == 200) {
//			String body = exchange.getMessage().getBody(String.class);
//			dingDto = new ObjectMapper().readValue(body.toString(), DingDto.class);
//			String umnHash = dingDto.getUmnHash();
//			System.out.println("final umnHash output ==>" + umnHash);
//			System.out.println("DONE!!");
//		} else {
//			System.out.println("fail to get!!");
//		}
//		return dingDto;
//	}

}