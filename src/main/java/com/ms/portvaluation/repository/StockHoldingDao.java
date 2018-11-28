package com.ms.portvaluation.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ms.portvaluation.entity.StockHolding;
/**
 * @author michael yin
 *
 */




@Repository
public class StockHoldingDao extends AbstractJpaDAO<StockHolding>{
	
	public  StockHoldingDao() {
		setClazz(StockHolding.class);
		
	}
	
	public List<StockHolding> searchStockHolding(){

		final StringBuilder queryStr = new StringBuilder(" from StockHolding c where 1=1 ");	
		final Query query = entityManager.createQuery(queryStr.toString());
		return query.getResultList();
	}

	
	

	

}
