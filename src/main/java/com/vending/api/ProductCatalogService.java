package com.vending.api;

import java.util.Map;
import java.util.Set;

import com.vending.exception.ProductNotFoundException;

public interface ProductCatalogService {

	public Set<String> retrieveProducts();
	
	public boolean isAnExistingProduct(String productName) throws ProductNotFoundException;
	
	public Map<String, Integer> updateProductQuantity(String productName);
	
	public boolean isProductSoldout(String productName);
}

