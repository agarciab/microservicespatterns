package com.viewnext.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.currencyexchangeservice.model.ExchangeValue;
import com.viewnext.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue
	(@PathVariable String from, @PathVariable String to){

		ExchangeValue exchangeValue =
				repository.findByFromAndTo(from, to);

		exchangeValue.setPort(
				Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
	}
}
