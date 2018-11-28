package com.ms.portvaluation.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author michael yin
 *
 */
public class TradeDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8780590041400387815L;
	private String stockTick;
	private BigDecimal price;
	private BigDecimal quantity;
	private String side;
	private String instrumentType;
	private Date tradeDate;
	
	private String underlying;
	private BigDecimal maturity;
	private BigDecimal strikePrice;
	
	private BigDecimal marketValue;
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
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getInstrumentType() {
		return instrumentType;
	}
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public BigDecimal getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}
	
	public String getUnderlying() {
		return underlying;
	}
	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}
	public BigDecimal getMaturity() {
		return maturity;
	}
	public void setMaturity(BigDecimal maturity) {
		this.maturity = maturity;
	}
	public BigDecimal getStrikePrice() {
		return strikePrice;
	}
	public void setStrikePrice(BigDecimal strikePrice) {
		this.strikePrice = strikePrice;
	}

}
