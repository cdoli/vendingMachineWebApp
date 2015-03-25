package com.vending.exception;

import java.io.Serializable;

import org.apache.commons.lang.exception.ExceptionUtils;

public class ProductPriceException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductPriceException()
	{
		super();		
	}

	public ProductPriceException(String message)
	{
		super(message);

	}

	public ProductPriceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ProductPriceException(String message, String rootCause)
	{
		super(message, new Throwable(rootCause));
	}


	public ProductPriceException(Throwable cause)
	{
		super(cause);
	}

	public String getDetailedMessage()
	{
		Throwable rootCause = ExceptionUtils.getRootCause(this);

		StringBuffer sb = new StringBuffer();

		sb.append(convertNullToUnknown(getMessage()));

		if (rootCause != null && rootCause != this)
		{
			sb.append(" (caused by) ");
			sb.append(convertNullToUnknown(rootCause.getMessage()));
		}

		return sb.toString();    
	}


	private static String convertNullToUnknown(String s)
	{
		if (s == null)
		{
			return "Unknown";
		}
		else
		{
			return s;
		}        
	}
}
