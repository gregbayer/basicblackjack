package com.gbayer.basicblackjack;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HandTest
{
	private Hand hand;

	@Before
	public void setUp() throws Exception
	{
		hand = new Hand();
	}

	@Test
	public void testAddCard()
	{
		hand.addCard(new Card(3));
		hand.addCard(new Card(1));
		hand.addCard(new Card(12));

		assertTrue(hand.getTotalHandValue() == 14);
	}

	@Test
	public void testClear()
	{
		hand.addCard(new Card(12));

		hand.clear();
		assertTrue(hand.getTotalHandValue() == 0);
	}

	@Test
	public void testToString()
	{
		hand.addCard(new Card(3));
		hand.addCard(new Card(1));
		hand.addCard(new Card(12));

		assertTrue(hand.toString().equals("3 A Q "));
	}

	@Test
	public void testToStringShowingTopCardOnly()
	{
		hand.addCard(new Card(3));
		hand.addCard(new Card(1));
		hand.addCard(new Card(12));

		assertTrue(hand.toStringShowingTopCardOnly().equals("3 X X "));
	}

}
