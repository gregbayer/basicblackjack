/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack
 * File: GameQuitExection.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */
package com.gbayer.basicblackjack;

/**
 * The exception thrown when a user wants to quit BasicBlackJack.
 * 
 * @author Greg Bayer <greg@gbayer.com>
 */
public class GameQuitException extends Exception
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new game quit exception.
	 */
	public GameQuitException()
	{

	}

	/**
	 * Instantiates a new game quit exception.
	 * 
	 * @param message
	 *            the message
	 */
	public GameQuitException(String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new game quit exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public GameQuitException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * Instantiates a new game quit exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public GameQuitException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
