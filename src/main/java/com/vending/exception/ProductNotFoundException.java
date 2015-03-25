package com.vending.exception;

import java.io.Serializable;

import org.apache.commons.lang.exception.ExceptionUtils;

public class ProductNotFoundException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException()
	{
		super();		
	}

	public ProductNotFoundException(String message)
	{
		super(message);

	}

	public ProductNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ProductNotFoundException(String message, String rootCause)
	{
		super(message, new Throwable(rootCause));
	}


	public ProductNotFoundException(Throwable cause)
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
