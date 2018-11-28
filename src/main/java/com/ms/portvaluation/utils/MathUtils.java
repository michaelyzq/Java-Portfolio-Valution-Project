package com.ms.portvaluation.utils;


import java.math.BigDecimal;
import java.math.MathContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author michael yin
 *
 */
public class MathUtils {
	private final static Logger LOG = LoggerFactory.getLogger(MathUtils.class);
	public static BigDecimal sqrt(BigDecimal num) {
		if (num.compareTo(BigDecimal.ZERO) < 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal x = num.divide(new BigDecimal("2"), MathContext.DECIMAL128);
		while (x.subtract(x = sqrtIteration(x, num)).abs().compareTo(new BigDecimal("0.0001")) > 0)
			;
		return x;
	}

	private static BigDecimal sqrtIteration(BigDecimal x, BigDecimal n) {
		return x.add(n.divide(x, MathContext.DECIMAL128)).divide(new BigDecimal("2"), MathContext.DECIMAL128);
	}

	// The Black and Scholes (1973) Stock option formula

	// S= Stock price
	// X=Strike price
	// T=Years to maturity
	// r= Risk-free rate
	// v=Volatility

	public static double Black76(String CallPutFlag, double S, double X, double T, double r, double v) {

		double d1, d2;
		double result;

		d1 = (Math.log(S / X) + (r + v * v / 2) * T) / (v * Math.sqrt(T));
		d2 = d1 - v * Math.sqrt(T);

		if (CallPutFlag.equals(ServiceConstant.CALL_OPTION_LETTER)) {
			result = S * CND(d1) - X * Math.exp(-r * T) * CND(d2);
		} else {
			result = X * Math.exp(-r * T) * CND(-d2) - S * CND(-d1);
		}

		LOG.warn("in Black76 result-[" + result + "] ");
		LOG.warn(CallPutFlag + ", $-" + S + ", x-" + X + ", T-" + T + ", r-" + r + ", v-" + v);

		return result;
	}

	// The cumulative normal distribution function
	public static double CND(double X) {
		double L, K, w;
		double a1 = 0.31938153, a2 = -0.356563782, a3 = 1.781477937, a4 = -1.821255978, a5 = 1.330274429;

		L = Math.abs(X);
		K = 1.0 / (1.0 + 0.2316419 * L);
		w = 1.0 - 1.0 / Math.sqrt(2.0 * Math.PI) * Math.exp(-L * L / 2)
				* (a1 * K + a2 * K * K + a3 * Math.pow(K, 3) + a4 * Math.pow(K, 4) + a5 * Math.pow(K, 5));

		if (X < 0.0) {
			w = 1.0 - w;
		}
		return w;
	}

}
