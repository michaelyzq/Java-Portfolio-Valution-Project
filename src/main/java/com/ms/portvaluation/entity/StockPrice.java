package com.ms.portvaluation.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author michael yin
 *
 */
@Entity
@Table(name = "STOCK_PRICE")
public class StockPrice implements Serializable {
	
	@Id
	@GeneratedValue
    private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name =  "STOCK_TICK")
	private String stockTick;
	private BigDecimal price;
	private Date updateTime;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


}
