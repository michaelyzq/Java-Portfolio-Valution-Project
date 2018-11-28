package com.ms.portvaluation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.portvaluation.json.RequestJson;
import com.ms.portvaluation.json.ResponseJson;
import com.ms.portvaluation.service.CsvService;

/**
 * @author michael yin
 *
 */

@RestController
@RequestMapping("/ProcessCSV")
public class CsvController {
	

	 
	 @Autowired
	 public CsvService csvService;
	
	 
	 @RequestMapping(value = "/post", method = RequestMethod.POST)
	 public ResponseJson getTest(@RequestBody RequestJson requestJson) throws Exception{
		 	String path = System.getProperty("user.dir");
			ResponseJson resp = new ResponseJson();
			return  resp;
	 }

}
