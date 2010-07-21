package com.gbayer.basicblackjack;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DeckTest
{
	private Deck deck;

	@Before
	public void setUp() throws Exception
	{
		deck = new Deck();
	}

	@Test
	public void testRemoveCard()
	{
		assertTrue(deck.numCardsRemaining() == 52);

		int threeCount = 0;
		int jackCount = 0;
		int aceCount = 0;
		for (int i = 0; i < 52; i++)
		{
			Card card = deck.removeCard();
			if (card.toString().equals("3"))
				threeCount++;
			if (card.toString().equals("J"))
				jackCount++;
			if (card.toString().equals("A"))
				aceCount++;

			// System.out.println(card);
		}
		assertTrue(threeCount == 4);
		assertTrue(jackCount == 4);
		assertTrue(aceCount == 4);

		assertTrue(deck.numCardsRemaining() == 0);

		deck.newDeck();

		assertTrue(deck.numCardsRemaining() == 52);
	}

}
