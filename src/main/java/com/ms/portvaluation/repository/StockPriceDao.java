package com.ms.portvaluation.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ms.portvaluation.entity.StockPrice;

/**
 * @author michael yin
 *
 */



@Repository
public class StockPriceDao extends AbstractJpaDAO<StockPrice>{
	
	public  StockPriceDao() {
		setClazz(StockPrice.class);
		
	}
	
	public List<StockPrice> retrieveLatestStockPrice() {
		List<StockPrice> stockPrices = new ArrayList<StockPrice>();
		Query query = entityManager.createNativeQuery(
				"select stock_price.* from stock_price, "
				+ "("
				+"select stock_tick stock_tick, max(update_time) update_time "
				+"from stock_price "
				+"group by stock_tick "
				+")  intertable "
				+" where stock_price.stock_tick= intertable.stock_tick "
				+"and  stock_price.update_time= intertable.update_time ");
				
		@SuppressWarnings("unchecked")
		List<Object[]> returnList = query.getResultList();
		for (Object[] a : returnList ) {
			StockPrice stockPrice = new StockPrice();
			stockPrice.setId(Long.valueOf(String.valueOf(a[0])));
			stockPrice.setPrice(new BigDecimal(String.valueOf(a[1])));
			stockPrice.setStockTick(String.valueOf(a[2]));
			
			stockPrices.add(stockPrice);
		}
		
		return stockPrices;
	}

	

}
