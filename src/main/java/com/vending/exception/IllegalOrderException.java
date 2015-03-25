package com.vending.exception;

import java.io.Serializable;

import org.apache.commons.lang.exception.ExceptionUtils;

public class IllegalOrderException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalOrderException()
	{
		super();		
	}

	public IllegalOrderException(String message)
	{
		super(message);

	}

	public IllegalOrderException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public IllegalOrderException(String message, String rootCause)
	{
		super(message, new Throwable(rootCause));
	}


	public IllegalOrderException(Throwable cause)
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
