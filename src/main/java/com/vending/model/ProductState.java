package com.vending.model;

import java.math.BigDecimal;

public class ProductState {
	
	public String productName;
	
	public Integer productQuantity;
	
	public BigDecimal insertedDollarAmount;
	
	public Boolean isProductSelected;

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productQuantity
	 */
	public Integer getProductQuantity() {
		return productQuantity;
	}

	/**
	 * @param productQuantity the productQuantity to set
	 */
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	/**
	 * @return the productPrice
	 */
	public BigDecimal getInsertedDollarAmount() {
		return insertedDollarAmount;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setInsertedDollarAmount(BigDecimal insertedDollarAmount) {
		this.insertedDollarAmount = insertedDollarAmount;
	}

	/**
	 * @return the isProductSelected
	 */
	public Boolean isProductSelected() {
		return isProductSelected;
	}

	/**
	 * @param isProductSelected the isProductSelected to set
	 */
	public void setProductSelected(Boolean isProductSelected) {
		this.isProductSelected = isProductSelected;
	}

}
