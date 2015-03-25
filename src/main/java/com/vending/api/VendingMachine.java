package com.vending.api;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import com.vending.exception.IllegalOrderException;
import com.vending.exception.ProductNotFoundException;
import com.vending.exception.ProductPriceException;

public interface VendingMachine {

	public Set<String> retrieveAllProducts() throws ProductNotFoundException;

	public Map<String, BigDecimal> retrieveProductsAndPrices() throws ProductPriceException;

	public boolean prepareOrder(BigDecimal dollarAmount, String productName) throws ProductNotFoundException, IllegalOrderException;

	public boolean confirmPurchase() throws IllegalOrderException;

	public boolean abortPurchase() throws IllegalOrderException;


}
