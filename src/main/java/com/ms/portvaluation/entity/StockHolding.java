package com.ms.portvaluation.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author michael yin
 *
 */


@Entity
@Table(name = "STOCK_HOLDING")
public class StockHolding implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
	
	@Column(name =  "STOCK_TICK")
	private String stockTick;
	private BigDecimal price;
	private BigDecimal volatility;
	
	@Column(name = "ANNUAL_RETURN")
	private BigDecimal annualReturn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockTick() {
		return stockTick;
	}
	public void setStockTick(String stockTick) {
		this.stockTick = stockTick;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getVolatility() {
		return volatility;
	}
	public void setVolatility(BigDecimal volatility) {
		this.volatility = volatility;
	}
	public BigDecimal getAnnualReturn() {
		return annualReturn;
	}
	public void setAnnualReturn(BigDecimal annualReturn) {
		this.annualReturn = annualReturn;
	}


}
