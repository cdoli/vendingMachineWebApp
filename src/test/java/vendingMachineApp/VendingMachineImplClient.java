package vendingMachineApp;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vending.api.VendingMachine;
import com.vending.api.VendingMachineImpl;
import com.vending.exception.IllegalOrderException;
import com.vending.exception.ProductNotFoundException;
import com.vending.exception.ProductPriceException;

/**
 * @author Charan
 *
 */
public class VendingMachineImplClient extends TestCase {
	private static final Logger log = LoggerFactory.getLogger(VendingMachineImplClient.class);
	private VendingMachine vendingMachine;
	protected void setUp() throws Exception {
		super.setUp();
		vendingMachine = new VendingMachineImpl();
		
	}
	
	@Test
	public void testRetrieveAllProducts()
	{
		try {
			Set<String> productSet = vendingMachine.retrieveAllProducts();
			log.info("The list of all available products are: ");
			for(String product : productSet)
			{
				log.info(product);
			}
		} catch (ProductNotFoundException e)
		{
			log.error(e.getDetailedMessage());
		}
	}
	
	@Test
	public void testRetrieveProductsAndPrices()
	{
		try {
			Map<String, BigDecimal> productPriceMap = vendingMachine.retrieveProductsAndPrices();
			log.info("The prices of available products: ");
			for (Entry<String, BigDecimal> productPriceMapEntry : productPriceMap.entrySet())
			{
			    	log.info( productPriceMapEntry.getKey()+" : $"+productPriceMapEntry.getValue().doubleValue());
			}
		} catch (ProductPriceException e) {
			log.error(e.getDetailedMessage());
		}
	}
	
	
	@Test
	public void testPlaceOrderAndConfirmPurchase()
	{
		try {
			boolean isOrderPlaced = vendingMachine.prepareOrder(new BigDecimal(9), "Apple");
			assertTrue(isOrderPlaced);
			boolean isOrderPurschaseConfirmed = vendingMachine.confirmPurchase();
			assertTrue(isOrderPurschaseConfirmed);
			
		} catch (ProductNotFoundException | IllegalOrderException e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * This test case intends to run the scenario where the an order is prepared and then aborted
	 */
	@Test
	public void testPlaceOrderAndAbortPurchase()
	{
		try {
			boolean isOrderPlaced = vendingMachine.prepareOrder(new BigDecimal(9), "Orange");
			assertTrue(isOrderPlaced);
			boolean isOrderPurschaseAborted = vendingMachine.abortPurchase();
			assertTrue(isOrderPurschaseAborted);			
		} catch (ProductNotFoundException | IllegalOrderException e) {
			log.error(e.getMessage());
		}
	}
	
	

}
