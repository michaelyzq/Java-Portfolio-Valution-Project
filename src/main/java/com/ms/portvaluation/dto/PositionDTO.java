package com.ms.portvaluation.dto;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author michael yin
 *
 */
public class PositionDTO implements Serializable{
	
	private String instrumentName;
	private BigDecimal marketValue;
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	public BigDecimal getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

}
