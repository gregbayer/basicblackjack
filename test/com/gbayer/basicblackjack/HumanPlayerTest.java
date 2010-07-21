package com.gbayer.basicblackjack;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HumanPlayerTest
{
	private HumanPlayer player;

	@Before
	public void setUp() throws Exception
	{
		player = new HumanPlayer("me", 500);
	}

	@Test
	public void testGetChipCount()
	{
		assertTrue(player.getChipCount() == 500);
	}

	@Test
	public void testWinBet()
	{
		player.betChips(100);
		player.winBet();
		assertTrue(player.getChipCount() == 600);
	}

	@Test
	public void testLooseBet()
	{
		player.betChips(30);
		player.looseBet();
		assertTrue(player.getChipCount() == 470);
	}

}
