/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack
 * File: Deck.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */
package com.gbayer.basicblackjack;

import java.util.Collections;
import java.util.Stack;

import org.apache.log4j.Logger;

/**
 * A <code>Deck</code> of <code>Cards</code> in play for the current
 * <code>Game</code>.
 */
public class Deck
{

	/** The Log4J logger. */
	private static Logger log = Logger.getLogger(Deck.class);

	/** The deck size. */
	private int deckSize;

	/** The cards. */
	private Stack<Card> cards;

	/** Constant - Size of one deck. */
	public static final int STANDARD_DECK_SIZE = 52;

	/**
	 * Constant - Size of deck at which a dealer might want to swap in a new
	 * deck.
	 */
	public static final int LOW_DECK_SIZE = 20;

	/**
	 * Instantiates a new deck.
	 */
	public Deck()
	{
		this(STANDARD_DECK_SIZE);
	}

	/**
	 * Instantiates a new deck.
	 * 
	 * @param deckSize
	 *            the deck size
	 */
	public Deck(int deckSize)
	{
		this.deckSize = deckSize;
		cards = new Stack<Card>();
		newDeck();
	}

	/**
	 * Clear existing deck and generate/shuffle a new one. Reuses underlying
	 * data structure.
	 */
	public void newDeck()
	{
		log.info("Generating new deck");

		cards.clear();

		// This loop supports blackjack games where more than one deck is used.
		// Use of more than one deck increases the house advantage.

		// Repeat for each multiple of STANDARD_DECK_SIZE
		for (int d = 0; d < (deckSize / STANDARD_DECK_SIZE); d++)
			for (int i = 0; i < STANDARD_DECK_SIZE; i++)
			{
				// Card values are from 1-13
				// Create 4 copies of each card value (one for each suit)
				cards.push(new Card(1 + (i % Card.MAX_CARD_ID)));
			}

		Collections.shuffle(cards);

		log.debug("Deck size is: " + cards.size());
	}

	/**
	 * Removes a card off the top of the deck.
	 * 
	 * @return the card
	 */
	public Card removeCard()
	{
		return cards.pop();
	}

	/**
	 * Count number of cards remaining.
	 * 
	 * @return the number of cards remaining.
	 */
	public int numCardsRemaining()
	{
		return cards.size();
	}
}
