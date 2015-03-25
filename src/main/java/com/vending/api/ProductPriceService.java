package com.vending.api;

import java.math.BigDecimal;
import java.util.Map;

import com.vending.exception.ProductNotFoundException;

public interface ProductPriceService {

	public Map<String, BigDecimal> retrieveProductPriceMap();
	
	public BigDecimal retrievePrice(String productName) throws ProductNotFoundException;
}
