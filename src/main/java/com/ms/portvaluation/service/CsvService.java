package com.ms.portvaluation.service;

import org.springframework.stereotype.Service;

import com.ms.portvaluation.json.RequestJson;
import com.ms.portvaluation.json.ResponseJson;
import com.ms.portvaluation.utils.CSVUtils;
/**
 * @author michael yin
 *
 */

@Service
public class CsvService {
	

	
	public ResponseJson readCSV(RequestJson requestJson) throws Exception {

		ResponseJson sampleResponseJson = new ResponseJson();
		String csvPath = requestJson.getFilePath();
		CSVUtils.readCSV(csvPath);
		return sampleResponseJson;
	}

}
