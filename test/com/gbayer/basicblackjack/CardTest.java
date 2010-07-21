package com.gbayer.basicblackjack;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CardTest
{
	private Card number;
	private Card face;
	private Card ace;

	@Before
	public void setUp()
	{
		number = new Card(3);
		face = new Card(11);
		ace = new Card(1);
	}

	@Test
	public void testGetCardValue()
	{
		boolean acesLow = true;
		assertTrue(number.getCardValue(acesLow) == 3);
		assertTrue(face.getCardValue(acesLow) == 10);
		assertTrue(ace.getCardValue(acesLow) == 1);

		acesLow = false;
		assertTrue(number.getCardValue(acesLow) == 3);
		assertTrue(face.getCardValue(acesLow) == 10);
		assertTrue(ace.getCardValue(acesLow) == 11);
	}

	@Test
	public void testToString()
	{
		assertTrue(number.toString().equals("3"));
		assertTrue(face.toString().equals("J"));
		assertTrue(ace.toString().equals("A"));
	}

}
