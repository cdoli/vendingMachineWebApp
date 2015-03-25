/**
 * 
 */
package com.vending.api;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vending.exception.IllegalOrderException;
import com.vending.exception.ProductNotFoundException;
import com.vending.exception.ProductPriceException;
import com.vending.model.ProductState;

/**
 * @author Charan
 *
 */
public class VendingMachineImpl implements VendingMachine {
	private static final Logger log = LoggerFactory.getLogger(VendingMachineImpl.class);
	private static ProductState productTransactionState = new ProductState();
	private ProductCatalogService catalogService = ProductCatalogServiceImpl.getProductCatalogService(); 
	private ProductPriceService pricingService = new ProductPriceServiceImpl(catalogService);
	
	
	
	

	/*
	 * Method to retrieve all available products
	 */
	public Set<String> retrieveAllProducts() throws ProductNotFoundException {
		log.info("Attempting to retrieve all the available products..");
		Set<String> productCatalog = catalogService.retrieveProducts();
		if (productCatalog.isEmpty())
		{
			log.error("Unable to retrieve products");
		}
	
		return productCatalog;

	}

	/*
	 * Method to retrieve a map of products and their corresponding prices
	 */
	public Map<String, BigDecimal> retrieveProductsAndPrices() throws ProductPriceException {
		log.info("Retrieving all the available products and their prices");
		Map<String, BigDecimal> productPriceMap = pricingService.retrieveProductPriceMap();
		if (productPriceMap.isEmpty()) {
			log.error("Price of the products not available");
			//throw new ProductPriceException("Price of the products not available");
		}
		return productPriceMap;
	}

	/*
	 * Method to enable the purchase of a product, This is the first step to purchase a product.
	 * This operation requires providing the dollar amount and a product name.
	 */
	public boolean prepareOrder(BigDecimal insertedDollarAmount,String productName) throws ProductNotFoundException, IllegalOrderException {
		boolean orderPlaced = false;
		log.info("Your order for "+productName+" is being processed");
			
			if (catalogService.isAnExistingProduct(productName) && !isProductSoldOut(productName)) {
				BigDecimal productPrice = pricingService.retrievePrice(productName);
				if (insertedDollarAmount != null && insertedDollarAmount.intValue() > 0) {
					if(insertedDollarAmount.compareTo(productPrice)!=-1)
					{
					productTransactionState.setProductSelected(new Boolean(true));
					productTransactionState.setProductName(productName);
					productTransactionState.setInsertedDollarAmount(insertedDollarAmount);
					orderPlaced = true;
					log.info("productTransactionState.isProductSelected  " +productTransactionState.isProductSelected.toString());
					log.info("Order prepared, you can either confirm the order by calling confirmPurchase() or abort the order");
					}
					else
					{
						log.error("Please insert enough money, you are short by :$"+Math.abs(productPrice.subtract(insertedDollarAmount).doubleValue()));
					}
				}
			} else if(isProductSoldOut(productName)) 
			{
				log.error("This vending machine is currently out of "+productName+"s");
				log.debug("Order not placed");
			}
		return orderPlaced;
	}

	private boolean isProductSoldOut(String productName) {
		return catalogService.isProductSoldout(productName);
	}

	/*
	 * Method to confirm the purchase after a product is selected and money inserted
	 * 
	 */
	public boolean confirmPurchase() throws IllegalOrderException {
		log.info("Your confirmed purchase is being processed");			
		boolean isProductPurchased = false;
		Map<String, Integer> productAvailabilityCatalog;
		if(productTransactionState != null && productTransactionState.isProductSelected()!= null && productTransactionState.isProductSelected().booleanValue())
		{
			productAvailabilityCatalog = catalogService.updateProductQuantity(productTransactionState.getProductName());
			isProductPurchased = true;
			log.info("You purchase of "+productTransactionState.getProductName()+" is completed successfully");
			log.info("The vending machine now has "+ productAvailabilityCatalog.get(productTransactionState.getProductName()).intValue() 
						+ " "+(productTransactionState.getProductName())+"s remaining ");
			
			//Resetting ProductState object
			productTransactionState = new ProductState();
		}
		else
		{
			log.error("Purchase not completed, please call the prepareOrder() operation to initiate an appropriate purchase");
		}
		return isProductPurchased;
	}

	/*
	 * Method to facilitate aborting a purchase after a product is selected and money inserted
	 */
	public boolean abortPurchase() throws IllegalOrderException {
		boolean isPurchaseAborted = false;
		log.info("Attempting to abort the purchase");
		if(productTransactionState != null && productTransactionState.isProductSelected()!= null && productTransactionState.isProductSelected().booleanValue())
		{
			isPurchaseAborted = true;
			//Resetting ProductState object
			productTransactionState = new ProductState();
			log.info("Purchase aborted successfully");
		}
		else
		{
			log.error("Please call the prepareOrder() operation to initiate the purchase");
		}
		return isPurchaseAborted;
	}

	/**
	 * This Setter method is to enable unit tests
	 * @param pricingService the pricingService to set
	 */
	public void setPricingService(ProductPriceService pricingService) {
		this.pricingService = pricingService;
	}

	/**
	 * This Setter method is to enable unit tests
	 * @param catalogService the catalogService to set
	 */
	public void setCatalogService(ProductCatalogService catalogService) {
		this.catalogService = catalogService;
	}
}
