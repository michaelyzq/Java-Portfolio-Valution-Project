package com.ms.portvaluation.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.google.gson.Gson;
import com.ms.portvaluation.dto.PositionDTO;
import com.ms.portvaluation.dto.TradeDTO;

/**
 * @author michael yin
 *
 */
public final class CSVUtils {
	private final static Logger LOG = LoggerFactory.getLogger(CSVUtils.class);
	
	public static String writeCSV(HashMap<String,PositionDTO>  positionDTOMap, BigDecimal totalNAV) {  
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		String dateNowStr = sdf.format(d);
		String csvFilePath = System.getProperty("user.dir")+"//"+dateNowStr+".csv";
	    try {  
	    	
	        CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));  

	        String[] csvHeaders = { "Position", "Market Value"};  
	        csvWriter.writeRecord(csvHeaders);  
	        Gson gson = new Gson();
	        LOG.warn(gson.toJson(positionDTOMap));
	        for(PositionDTO v: positionDTOMap.values()){
	        	String[] csvContent = { v.getInstrumentName(), String.valueOf(v.getMarketValue().setScale(2, BigDecimal.ROUND_HALF_UP)) };
	        	csvWriter.writeRecord(csvContent); 
	        }
	        String[] csvContent = {"Total NAV",String.valueOf(totalNAV.setScale(2, BigDecimal.ROUND_HALF_UP)) };
        	csvWriter.writeRecord(csvContent); 
	        csvWriter.close();
	        return csvFilePath;
	    } catch (IOException e) {  
	        e.printStackTrace();  
	        return "File Generate error";
	    }  
	}  
	
	public static List<TradeDTO> readCSV(String csvFilePath) {  
	    try {  

	        ArrayList<String[]> csvFileList = new ArrayList<String[]>();  
	        CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));  

	        reader.readHeaders();  

	        while (reader.readRecord()) {  
	        	LOG.warn(reader.getRawRecord());   
	            csvFileList.add(reader.getValues());   
	        }  
	        reader.close();  
	          
	        List<TradeDTO> tradeDTOs = new ArrayList<TradeDTO>();
	        
	        for (int row = 0; row < csvFileList.size(); row++) {  
	        	TradeDTO tradeDTO = new TradeDTO();
	        	tradeDTO.setStockTick(csvFileList.get(row)[0]);  
	        	tradeDTO.setQuantity(new BigDecimal(csvFileList.get(row)[1]));  
	        	tradeDTO.setSide(csvFileList.get(row)[2]);
	        	tradeDTO.setInstrumentType(csvFileList.get(row)[3]);
	        	DateFormat df = new SimpleDateFormat(ServiceConstant.SIMPLE_DATE_FORMAT );
	        	try {
					tradeDTO.setTradeDate(df.parse(csvFileList.get(row)[4]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        	tradeDTO.setUnderlying(csvFileList.get(row)[5]);
	        	if (StringUtils.isNotBlank(csvFileList.get(row)[6])){
	        		tradeDTO.setMaturity(new BigDecimal(csvFileList.get(row)[6]));
	        	}
	        	if (StringUtils.isNotBlank(csvFileList.get(row)[7])) {
	        		tradeDTO.setStrikePrice(new BigDecimal(csvFileList.get(row)[7]));
	        	}
	            tradeDTOs.add(tradeDTO); 
	        }  
	        
	        return tradeDTOs;
	    } catch (IOException e) {  
	        return new ArrayList<TradeDTO>();
	    }  
	} 

}
