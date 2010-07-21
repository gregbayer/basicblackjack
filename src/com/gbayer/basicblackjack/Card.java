/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack
 * File: Card.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */
package com.gbayer.basicblackjack;

import org.apache.log4j.Logger;

/**
 * A Card within a <code>Deck</code> or a <code>Hand</code>
 */
public class Card
{

	/** The Log4J logger. */
	private static Logger log = Logger.getLogger(Card.class);

	/** The card id (1-13). */
	private int card;

	/**
	 * Instantiates a new card.
	 * 
	 * @param card
	 *            the card
	 */
	public Card(int card)
	{
		this.card = card;
	}

	/**
	 * Gets the card's value.
	 * 
	 * @param acesLow
	 *            indicates whether to treat an ace's as value 1 or 11)
	 * @return the card value
	 */
	public int getCardValue(boolean acesLow)
	{
		if (card > 10)
			return 10; // All face cards are worth 10

		if (card == 1) // Ace has value of 1 or 11
			if (acesLow)
			{
				log.debug("Using low ace");
				return 1;
			} else
			{
				log.debug("Using high ace");
				return 11;
			}

		return card;
	}

	/**
	 * Generates character representation of card.
	 * 
	 * @return character representation of card
	 */
	public String toString()
	{
		switch (card)
		{
		case 1:
			return "A";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";
		default:
			return Integer.toString(card);
		}
	}
}
