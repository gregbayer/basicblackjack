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
 * A <code>Deck</code> of <code>Cards</code> in play for the current <code>Game</code>.
 */
public class Deck
{

	/** The Log4J logger. */
	private static Logger log = Logger.getLogger(Deck.class);

	/** The deck size. */
	private int deckSize;

	/** The cards. */
	private Stack<Card> cards;

	/**
	 * Instantiates a new deck.
	 */
	public Deck()
	{
		this(52);
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
		// Repeat for each multiple of 52 cards (assumes one deck is 52 cards)
		for (int d = 0; d < (deckSize / 52); d++)
			for (int i = 0; i < 52; i++)
			{
				// Card values are from 1-13
				// Create 4 copies of each card value (one for each suit)
				cards.push(new Card(1 + (i % 13)));
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
