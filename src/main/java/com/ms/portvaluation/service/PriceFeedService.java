package com.ms.portvaluation.service;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.portvaluation.entity.StockHolding;
import com.ms.portvaluation.entity.StockPrice;
import com.ms.portvaluation.json.RequestJson;
import com.ms.portvaluation.json.ResponseJson;
import com.ms.portvaluation.repository.StockHoldingDao;
import com.ms.portvaluation.repository.StockPriceDao;
import com.ms.portvaluation.utils.MathUtils;
import com.ms.portvaluation.utils.ServiceConstant;
import com.ms.portvaluation.utils.TimeUtils;



/**
 * @author michael yin
 *
 */
@Service
public class PriceFeedService {
	
	private final static Logger LOG = LoggerFactory.getLogger(PriceFeedService.class);
	
	public static boolean PRICE_OPEN;
	

	
	@Autowired
	StockPriceDao stockPriceDao;
	
	@Autowired
	StockHoldingDao stockHoldingDao;
	
	

	public ResponseJson insertPrice(RequestJson requestJson) throws Exception {
		List<StockHolding> holdingList = stockHoldingDao.searchStockHolding();
		PRICE_OPEN = true;
		priceInsert(requestJson, holdingList);
		ResponseJson sampleResponseJson = new ResponseJson();
		return sampleResponseJson;
	}
	
	

	public ResponseJson closePriceFeed(RequestJson requestJson) throws Exception {
		PRICE_OPEN = false;
		ResponseJson sampleResponseJson = new ResponseJson();
		return sampleResponseJson;
	}
	
	
	
	public void priceInsert(RequestJson requestJson, List<StockHolding> holdingList){

		Timer timer = new Timer();
		HashMap<String,BigDecimal> latestPriceMap = new HashMap<String,BigDecimal>();
		for (int i = 0 ; i<holdingList.size(); i++ ){
			latestPriceMap.put(holdingList.get(i).getStockTick(), holdingList.get(i).getPrice());
			
		}
		Random randomno = new Random();
		timer.schedule(new TimerTask() {
			public void run() {
				if (PRICE_OPEN ==true){
					for (int i = 0 ; i<holdingList.size(); i++ ){
						StockHolding stockHolding = holdingList.get(i);
						BigDecimal latestPrice = latestPriceMap.get(stockHolding.getStockTick());
						BigDecimal volatility = stockHolding.getVolatility();
						BigDecimal annualReturn = stockHolding.getVolatility();
						BigDecimal leftPart = annualReturn.multiply(TimeUtils.getRandomTimeInterval()).divide(ServiceConstant.STOCK_B,10,RoundingMode.HALF_UP);
						BigDecimal rightPart = BigDecimal.valueOf(randomno.nextGaussian()).
								multiply(volatility).multiply
								(MathUtils.sqrt(ServiceConstant.STOCK_A.divide(ServiceConstant.STOCK_B,10,RoundingMode.HALF_UP)));
						latestPrice = latestPrice.multiply(BigDecimal.valueOf(1).add(leftPart).add(rightPart));
						//latestPrice = latestPrice.setScale(4,BigDecimal.ROUND_HALF_UP);
						StockPrice entity = new StockPrice();
						entity.setPrice(latestPrice );
						entity.setStockTick(stockHolding.getStockTick());
						entity.setUpdateTime(new Date());
						stockPriceDao.create(entity);
						latestPriceMap.put(stockHolding.getStockTick(), latestPrice);
						
					}
					ValueChangeService.updatePrice(latestPriceMap);
		
					
					
				}
				else{
					timer.cancel();
					return;
				}
			}
		}, 0, 10000);
		return;
	
		
	}
	
	

}
