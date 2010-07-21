package com.gbayer.basicblackjack;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameTest
{
	private Game game;

	@Before
	public void setUp() throws Exception
	{
		game = new Game();
	}

	@Test
	public void testGetDeck()
	{
		assertTrue(game.getDeck().numCardsRemaining() == 52);
	}

}
