/**
 * 
 */
package com.vending.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.vending.exception.ProductNotFoundException;

/**
 * @author Charan
 *
 */
public class ProductPriceServiceImpl implements ProductPriceService {
	private ProductCatalogService catalogService;
	
	public ProductPriceServiceImpl (ProductCatalogService catalogService)
	{
		this.catalogService = catalogService;
	}
	

	/* A dummy implementation to the Product Price service 
	 */
	public Map<String, BigDecimal> retrieveProductPriceMap() {
		Map<String, BigDecimal> productPriceMap = new HashMap<String, BigDecimal>();
		
		for(String product : catalogService.retrieveProducts())
		{
			if(StringUtils.equals("Apple", product))
			{
				productPriceMap.put(product, new BigDecimal(9));
			}
			if(StringUtils.equals("Mango", product))
			{
				productPriceMap.put(product, new BigDecimal(8));
			}
			if(StringUtils.equals("Peach", product))
			{
				productPriceMap.put(product, new BigDecimal(7));
			}
			if(StringUtils.equals("Banana", product))
			{
				productPriceMap.put(product, new BigDecimal(6));
			}
			if(StringUtils.equals("Orange", product))
			{
				productPriceMap.put(product,new BigDecimal(5));
			}
			
		}
		
		return productPriceMap;
	}

	public BigDecimal retrievePrice(String productName) throws ProductNotFoundException {
		Map<String, BigDecimal> productPriceMap = retrieveProductPriceMap();
		BigDecimal price =	productPriceMap.get(productName);
		if(price == null)
		{
			throw new ProductNotFoundException("Product not sold here!");
		}
		return price;
	}

}
