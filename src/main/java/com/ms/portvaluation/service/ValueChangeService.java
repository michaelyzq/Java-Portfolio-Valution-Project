package com.ms.portvaluation.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.ms.portvaluation.dto.PositionDTO;
import com.ms.portvaluation.dto.TradeDTO;
import com.ms.portvaluation.entity.StockHolding;
import com.ms.portvaluation.entity.StockPrice;
import com.ms.portvaluation.utils.ValuationUtils;
/**
 * @author michael yin
 *
 */
public class ValueChangeService {
	
	public static List<TradeDTO> trades;
	
	public static List<StockHolding> holdingList;

	
	private final static Logger LOG = LoggerFactory.getLogger(PortfolioValuationService.class);
	
	public static void setTradeDTO(List<TradeDTO> trades) {
		ValueChangeService.trades = trades;
	}
	
	public static void setHoldingList(List<StockHolding> holdingList) {
		ValueChangeService.holdingList =  holdingList;
	}
	
	
	
	public static void updatePrice(HashMap<String,BigDecimal> latestPriceMap ) {
		if (ValueChangeService.trades == null) {
			LOG.warn("Waiting for trade csv upload");
		}else {
			Gson gson = new Gson();

			List<StockPrice> stockPrices = new ArrayList<StockPrice>();
			Iterator<Map.Entry<String,BigDecimal>> entries = latestPriceMap.entrySet().iterator();
			 while (entries.hasNext()) { 
				   Map.Entry<String,BigDecimal> entry = entries.next(); 
				   StockPrice stockPrice = new StockPrice();
				   stockPrice.setPrice(entry.getValue());
				   stockPrice.setStockTick(entry.getKey());	
				   stockPrices.add(stockPrice);
		     } 
			
			List<TradeDTO> processTrades = ValuationUtils.processTradeValuation(stockPrices, trades,ValueChangeService.holdingList);
			HashMap<String, PositionDTO> positionDTOMap = new HashMap<String, PositionDTO>();
			BigDecimal totalNAV = BigDecimal.valueOf(0);

			totalNAV = ValuationUtils.proceePortfolioValuation(positionDTOMap, processTrades);

			LOG.warn(MessageFormat.format("Total NAV Chage to {0}",gson.toJson(totalNAV.setScale(2, BigDecimal.ROUND_UP))));
			stockPrices = null;
			entries  = null;
			

		}
			
		
	}
	

	
	
}
