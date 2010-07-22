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

	/** Constant - Value of any face card. */
	public static final int FACE_CARD_VALUE = 10;

	/** Constant - Low value of an ace. */
	public static final int LOW_ACE_VALUE = 1;

	/** Constant - High value of an ace. */
	public static final int HIGH_ACE_VALUE = 11;

	/** Constant - Card id representing an ace. */
	public static final int ACE_ID = 1;

	/** Constant - Card id representing an jack. */
	public static final int JACK_ID = 11;

	/** Constant - Card id representing an queen. */
	public static final int QUEEN_ID = 12;

	/** Constant - Card id representing an king. */
	public static final int KING_ID = 13;

	/** Constant - All cards with ids equal or greater are face cards. */
	public static final int FIRST_FACE_CARD_ID = JACK_ID;

	/** Constant - Number of unique card ids. */
	public static final int MAX_CARD_ID = 13;

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
		if (card >= FIRST_FACE_CARD_ID)
			return FACE_CARD_VALUE; // All face cards are worth 10

		if (card == ACE_ID) // Ace has value of 1 or 11
			if (acesLow)
			{
				log.debug("Using low ace");
				return LOW_ACE_VALUE;
			} else
			{
				log.debug("Using high ace");
				return HIGH_ACE_VALUE;
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
		case ACE_ID:
			return "A";
		case JACK_ID:
			return "J";
		case QUEEN_ID:
			return "Q";
		case KING_ID:
			return "K";
		default:
			return Integer.toString(card);
		}
	}
}
