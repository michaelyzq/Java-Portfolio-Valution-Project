package com.ms.portvaluation.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.ms.portvaluation.dto.PositionDTO;
import com.ms.portvaluation.dto.TradeDTO;
import com.ms.portvaluation.entity.StockHolding;
import com.ms.portvaluation.entity.StockPrice;
/**
 * @author michael yin
 *
 */
public class ValuationUtils {
	private final static Logger LOG = LoggerFactory.getLogger(ValuationUtils.class);
	

	public static List<TradeDTO>  processTradeValuation(List<StockPrice> stockPrices, List<TradeDTO> trades,List<StockHolding> holdingList) {
		HashMap<String, BigDecimal> priceMap = new HashMap<String, BigDecimal>();
				
		HashMap<String, BigDecimal> volatityMap = new HashMap<String, BigDecimal>();
		for (StockPrice stockPrice : stockPrices) {
			priceMap.put(stockPrice.getStockTick(), stockPrice.getPrice());
		}
		for (StockHolding stockHolding : holdingList) {
			volatityMap.put(stockHolding.getStockTick(), stockHolding.getVolatility());
		}
		for (TradeDTO tradeDTO : trades) {
			if (priceMap.containsKey(tradeDTO.getStockTick())) {
				tradeDTO.setMarketValue(tradeDTO.getQuantity().multiply(priceMap.get(tradeDTO.getStockTick())));
			}
			if (tradeDTO.getInstrumentType().equals(ServiceConstant.CALL_OPTION)
					&& priceMap.containsKey(tradeDTO.getUnderlying())
					&& volatityMap.containsKey(tradeDTO.getUnderlying())) {
				Double marketValue = MathUtils.Black76(ServiceConstant.CALL_OPTION_LETTER, priceMap.get(tradeDTO.getUnderlying()).doubleValue(),
						tradeDTO.getStrikePrice().doubleValue(), tradeDTO.getMaturity().doubleValue(),
						ServiceConstant.RISK_FREE_RATE, volatityMap.get(tradeDTO.getUnderlying()).doubleValue());
				tradeDTO.setMarketValue(tradeDTO.getQuantity().multiply(BigDecimal.valueOf(marketValue).setScale(2,BigDecimal.ROUND_HALF_UP)));
			}
			if (tradeDTO.getInstrumentType().equals(ServiceConstant.PUT_OPTION)
					&& priceMap.containsKey(tradeDTO.getUnderlying())
					&& volatityMap.containsKey(tradeDTO.getUnderlying())) {
				Double marketValue = MathUtils.Black76(ServiceConstant.PUT_OPTION_LETTER, priceMap.get(tradeDTO.getUnderlying()).doubleValue(),
						tradeDTO.getStrikePrice().doubleValue(), tradeDTO.getMaturity().doubleValue(),
						ServiceConstant.RISK_FREE_RATE, volatityMap.get(tradeDTO.getUnderlying()).doubleValue());
				tradeDTO.setMarketValue(tradeDTO.getQuantity().multiply(BigDecimal.valueOf(marketValue).setScale(2,BigDecimal.ROUND_HALF_UP)));
			}

		}
		return trades;

	}

	public static BigDecimal proceePortfolioValuation(HashMap<String, PositionDTO> positionDTOMap, List<TradeDTO> trades
			) {
		BigDecimal totalNAV = BigDecimal.valueOf(0.00);
		for (TradeDTO tradeDTO : trades) {
			String stockTick = tradeDTO.getStockTick();
			if (positionDTOMap.containsKey(tradeDTO.getStockTick())) {
				PositionDTO positionDTO = positionDTOMap.get(stockTick);
				positionDTO.setMarketValue(positionDTO.getMarketValue().add(tradeDTO.getMarketValue()));
				positionDTOMap.put(stockTick, positionDTO);
				totalNAV = totalNAV.add(tradeDTO.getMarketValue());
			} else {
				PositionDTO positionDTO = new PositionDTO();
				
				positionDTO.setMarketValue(tradeDTO.getMarketValue());
				positionDTO.setInstrumentName(tradeDTO.getStockTick());
				positionDTOMap.put(stockTick, positionDTO);
				totalNAV = totalNAV.add(tradeDTO.getMarketValue());

			}
		}		
		return totalNAV;

	}
}
