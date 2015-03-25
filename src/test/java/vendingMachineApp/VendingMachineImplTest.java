package vendingMachineApp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.junit.Test;

import com.vending.api.ProductCatalogService;
import com.vending.api.ProductCatalogServiceImpl;
import com.vending.api.ProductPriceService;
import com.vending.api.ProductPriceServiceImpl;
import com.vending.api.VendingMachineImpl;
import com.vending.exception.IllegalOrderException;
import com.vending.exception.ProductNotFoundException;
import com.vending.exception.ProductPriceException;

/**
 * @author Charan
 *
 */

public class VendingMachineImplTest extends TestCase {
	private VendingMachineImpl vendingMachine;
	private ProductCatalogService mockCatalogService;
	private ProductPriceService mockPricingService; 

	
	protected void setUp() throws Exception {
		super.setUp();
		vendingMachine = new VendingMachineImpl();
		
		
		
		
	}
	
	/**
	 * Test case for exception 
	 * @throws ProductNotFoundException
	 */
	@Test(expected = ProductNotFoundException.class)
	public void testRetrieveAllProducts() throws ProductNotFoundException {
		mockCatalogService = EasyMock.createMock(ProductCatalogServiceImpl.class);
		vendingMachine.setCatalogService(mockCatalogService);
		EasyMock.expect(mockCatalogService.retrieveProducts()).andReturn(new HashSet<String>());
		EasyMock.replay(mockCatalogService);
		vendingMachine.retrieveAllProducts();
		EasyMock.verify(mockCatalogService);
	}
	
	
	/**
	 * Test case for exception 
	 * @throws ProductPriceException
	 */
	@Test(expected = ProductPriceException.class)
	public void testRetrieveProductsAndPrices() throws ProductPriceException 
	{
			mockPricingService = EasyMock.createMock(ProductPriceServiceImpl.class);
			vendingMachine.setPricingService(mockPricingService);
			EasyMock.expect(mockPricingService.retrieveProductPriceMap()).andReturn(new HashMap<String, BigDecimal>());
			EasyMock.replay(mockPricingService);
			vendingMachine.retrieveProductsAndPrices();
			EasyMock.verify(mockPricingService);
	}
	
	
	/**
	 * Test case to fail a prepareOrder() when the amount entered is not enough for a product
	 * @throws ProductNotFoundException
	 * @throws IllegalOrderException 
	 */
	@Test
	public void testPrepareOrder_NotEnoughMoney() throws ProductNotFoundException, IllegalOrderException
	{
		boolean isOrderReady = vendingMachine.prepareOrder(new BigDecimal(2), "Apple");
		
		assertFalse(isOrderReady);
		
	}
	
	
	/**
	 * This test case intends to test the scenario where the vending machine runs out of a product 
	 * @throws ProductNotFoundException 
	 * @throws IllegalOrderException 
	 */
	@Test(expected = IllegalOrderException.class)
	public void testPlaceOrderAndConfirmPurchase_NotEnoughProducts() throws ProductNotFoundException, IllegalOrderException
	{
		
			boolean isOrderPlaced = vendingMachine.prepareOrder(new BigDecimal(9), "Apple");
			assertTrue(isOrderPlaced);
			boolean isOrderPurschaseConfirmed = vendingMachine.confirmPurchase();
			assertTrue(isOrderPurschaseConfirmed);
			
			boolean isOrderPlaced1 = vendingMachine.prepareOrder(new BigDecimal(9), "Apple");
			assertFalse(isOrderPlaced1);
	}

	/**
	 * This vending machine requires an order to be prepared (by calling the prepareOrder())
	 * before confirming the purchase. This test case intends to fail when a confirmPurchase operation 
	 * is invoked before prepareOrder()  
	 * @throws IllegalOrderException 
	 */
	@Test(expected = IllegalOrderException.class )
	public void testConfirmPurchase_WhenNotPrepared() throws IllegalOrderException
	{
		boolean isPurchaseConfirmed = vendingMachine.confirmPurchase();	
		assertFalse(isPurchaseConfirmed);
	}
	
	/**
	 * This vending machine requires an order to be prepared (by calling the prepareOrder())
	 * before aborting the purchase. This test case intends to fail when an abort operation 
	 * is invoked before prepareOrder()  
	 * @throws IllegalOrderException 
	 */
	@Test(expected = IllegalOrderException.class )
	public void testAbortPurchase_WhenNotPrepared() throws IllegalOrderException
	{
		boolean isPurchaseAborted = vendingMachine.abortPurchase();	
		assertFalse(isPurchaseAborted);
	}
	
	
}
