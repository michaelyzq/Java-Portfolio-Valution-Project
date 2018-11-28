package com.ms.portValuation;

import org.junit.Assert;
import org.junit.Test;

import com.ms.portvaluation.utils.MathUtils;

public class MathUtilsTest {

	@Test
	public void testInTheMoneyCallOption() {
		Assert.assertTrue(MathUtils.Black76("c", 100.0,90.0, 1.0, 0.02, 0.05)>5);
	}
	
	@Test
	public void testInTheMoneyPutOption() {
		Assert.assertTrue(MathUtils.Black76("p", 100.0,110.0, 1.0, 0.02, 0.05)>5);
	}
	
	@Test
	public void testOutOfTheMoneyCallOption() {
		Assert.assertTrue(MathUtils.Black76("c", 100.0,200, 1.0, 0.02, 0.05)==0);
	}
	
	@Test
	public void testOutOfTheMoneyPutOption() {
		Assert.assertTrue(MathUtils.Black76("p", 100.0,50, 1.0, 0.02, 0.05)==0);
	}

}
