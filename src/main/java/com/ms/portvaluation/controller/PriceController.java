package com.ms.portvaluation.controller;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.portvaluation.json.RequestJson;
import com.ms.portvaluation.json.ResponseJson;
import com.ms.portvaluation.service.PriceFeedService;

/**
 * @author michael yin
 *
 */

@RestController
@RequestMapping("/PriceCSV")
public class PriceController {
	
	 private static final String template = "Hello, %s!";
	 private final AtomicLong counter = new AtomicLong();
	 

	

	 @Autowired
	 public PriceFeedService priceFeedService;
 
	 @RequestMapping(value = "/startPrice", method = RequestMethod.POST)
	 public ResponseJson startPriceFeed(@RequestBody RequestJson requestJson) throws Exception{

			ResponseJson resp = priceFeedService.insertPrice(requestJson);
			resp.setRespMessage("Price Feed Open");
			return  resp;
	 }
	 
	 @RequestMapping(value = "/closePrice", method = RequestMethod.POST)
	 public ResponseJson closePriceFeed(@RequestBody RequestJson requestJson) throws Exception{

			ResponseJson resp = priceFeedService.closePriceFeed(requestJson);
			resp.setRespMessage("Price Feed Close");
			return  resp;
	 }

}
