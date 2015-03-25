package com.vending.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.vending.exception.ProductNotFoundException;

/**
 * @author Charan
 *
 */
public class ProductCatalogServiceImpl implements ProductCatalogService {
	private static ProductCatalogServiceImpl catalogServiceSingleton = null;
	private static Map<String, Integer> productAvailabilityCatalog;
	
	// A simple ( not the best ) way to populate the product catalog 
	static
	{
		productAvailabilityCatalog = new HashMap<String, Integer>();
		productAvailabilityCatalog.put("Apple", new Integer(1));
		productAvailabilityCatalog.put("Mango" , new Integer(5));
		productAvailabilityCatalog.put("Peach", new Integer(5));
		productAvailabilityCatalog.put("Banana", new Integer(5));
		productAvailabilityCatalog.put("Orange", new Integer(5));	
	}
	private ProductCatalogServiceImpl()
	{
		if(catalogServiceSingleton != null)
				throw new IllegalStateException("Product Catalog Service is already initialized");
	}
	
	public static ProductCatalogServiceImpl getProductCatalogService(){
		ProductCatalogServiceImpl catalogService = catalogServiceSingleton;
		if(catalogService == null)
		{
			synchronized (ProductCatalogServiceImpl.class){
				catalogService = catalogServiceSingleton;
				if(catalogService == null){
					catalogServiceSingleton = catalogService = new ProductCatalogServiceImpl();
				}
			}
		}
		return catalogService;
	}
	
	
	/*
	 * 
	 * This is a dummy implementation of the a service to retrieve a catalog of products
	 */
	public Set<String> retrieveProducts() {
		return productAvailabilityCatalog.keySet();
	}

	public boolean isAnExistingProduct(String productName)
			throws ProductNotFoundException {
		boolean productExists = false;
		Set<String> productCatalog = retrieveProducts();
		if(StringUtils.isNotEmpty(productName))
		{
			productExists = productCatalog.contains(productName);			
		}
		
		return productExists;
	}
	
	public Map<String, Integer> updateProductQuantity(String productName)
	{
		for (Entry<String, Integer> productAvailabilityCatalogEntry : productAvailabilityCatalog.entrySet())
		{
		    if(StringUtils.equals(productAvailabilityCatalogEntry.getKey(), productName))
		    {
		    	if(productAvailabilityCatalogEntry.getValue().intValue()>0)
		    	{
		    	productAvailabilityCatalog.put(productName, productAvailabilityCatalogEntry.getValue().intValue()-1);
		    	}
		    }
		}
		return productAvailabilityCatalog;
	}

	public boolean isProductSoldout(String productName) {
		boolean isProductSoldout = false;
		for (Entry<String, Integer> productAvailabilityCatalogEntry : productAvailabilityCatalog.entrySet())
		{
		    if(StringUtils.equals(productAvailabilityCatalogEntry.getKey(), productName))
		    {
		    	if(productAvailabilityCatalogEntry.getValue().intValue()<=0)
		    	{
		    		isProductSoldout =  true;
		    	}
		    }
		}
		return isProductSoldout;
	}
	

}
