package com.ms.portvaluation.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ms.portvaluation.dto.PositionDTO;
import com.ms.portvaluation.dto.TradeDTO;
import com.ms.portvaluation.entity.StockHolding;
import com.ms.portvaluation.entity.StockPrice;
import com.ms.portvaluation.json.RequestJson;
import com.ms.portvaluation.json.ResponseJson;
import com.ms.portvaluation.repository.StockHoldingDao;
import com.ms.portvaluation.repository.StockPriceDao;
import com.ms.portvaluation.utils.CSVUtils;
import com.ms.portvaluation.utils.ValuationUtils;
/**
 * @author michael yin
 *
 */
@Service
public class PortfolioValuationService {

	private final static Logger LOG = LoggerFactory.getLogger(PortfolioValuationService.class);

	public static boolean PRICE_OPEN;
	

	@Autowired
	StockPriceDao stockPriceDao;

	@Autowired
	StockHoldingDao stockHoldingDao;

	public ResponseJson getLatestPrice(RequestJson requestJson) throws Exception {
		ResponseJson sampleResponseJson = new ResponseJson();
		List<StockPrice> stockPrices = stockPriceDao.retrieveLatestStockPrice();
		if(stockPrices.size() ==0) {
			sampleResponseJson.setRespMessage(MessageFormat.format("Please turn on price feed!!", ""));
			LOG.error("Please turn on price feed!!");	
			return sampleResponseJson;
			
		}
		List<StockHolding> holdingList = stockHoldingDao.searchStockHolding();
		Gson gson = new Gson();
		
		
		String filePath = System.getProperty("user.dir") + requestJson.getFilePath();
		List<TradeDTO> trades = loadTradeDTO(filePath);
		ValueChangeService.setTradeDTO(trades);
		ValueChangeService.setHoldingList(holdingList);
		trades = ValuationUtils.processTradeValuation(stockPrices, trades,holdingList);
		HashMap<String, PositionDTO> positionDTOMap = new HashMap<String, PositionDTO>();
		BigDecimal totalNAV = BigDecimal.valueOf(0);
		totalNAV = ValuationUtils.proceePortfolioValuation(positionDTOMap, trades);
		String message = CSVUtils.writeCSV(positionDTOMap, totalNAV);
		sampleResponseJson.setRespMessage(MessageFormat.format("File Available at {0}", message));

		return sampleResponseJson;

	}

	public List<TradeDTO> loadTradeDTO(String path) throws Exception {

		return CSVUtils.readCSV(path);
	}

	

}
