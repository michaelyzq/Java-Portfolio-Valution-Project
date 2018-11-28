package com.ms.portvaluation.utils;

import java.math.BigDecimal;

/**
 * @author michael yin
 *
 */
public final class ServiceConstant {

	private ServiceConstant() { }

	public final static String NA = "N/A";
	public final static String ISO_8601_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public final static String HTTP_ATTRIBUTE_CORRELATION_ID = "HTTP_ATTRIBUTE_CORRELATION_ID";
	public final static String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";


	
	public final static Long ACTIVE_FLAG_ACTIVE = 1L;
	public final static Long ACTIVE_FLAG_INACTIVE = 0L;

	public final static Long RECORD_DELETED = 1L;
	public final static Long RECORD_NOT_DELETED = 0L;

	public final static Long RECORD_STATUS_ACTIVE = 1L;
	public final static Long RECORD_STATUS_INACTIVE = 0L;
	
	public final static String APPROVAL_USER_INPUT= "User Input";
	public final static String APPROVAL_DOCUMENT_UPLOAD= "Document Upload";

	public final static BigDecimal STOCK_A =BigDecimal.valueOf(5);
	public final static BigDecimal STOCK_B =BigDecimal.valueOf(7257600);
	
	public final static String STOCK = "Stock";
	public final static String CALL_OPTION = "CallOption";
	public final static String PUT_OPTION = "PutOption";
	
	public final static String CALL_OPTION_LETTER = "c";
	public final static String PUT_OPTION_LETTER = "p";
	
	public final static Double RISK_FREE_RATE = Double.valueOf("0.02");
	
	

}
