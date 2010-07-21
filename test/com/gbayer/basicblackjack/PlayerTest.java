package com.gbayer.basicblackjack;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest
{
	private Player player;

	@Before
	public void setUp() throws Exception
	{
		player = new HumanPlayer("me", 500);
	}

	@Test
	public void testNewHand()
	{
		player.getHand().addCard(new Card(13));
		assertTrue(player.getHand().getTotalHandValue() == 10);

		player.newHand();
		assertTrue(player.getHand().getTotalHandValue() == 0);
	}

	@Test
	public void testDealCard()
	{
		player.dealCard(new Card(13));
		assertTrue(player.getHand().getTotalHandValue() == 10);
	}

	@Test
	public void testGetName()
	{
		assertTrue(player.getName().equals("me"));
	}

	@Test
	public void testToString()
	{
		assertTrue(player.toString().equals("me: Current Hand: "));

		player.dealCard(new Card(3));
		player.dealCard(new Card(1));

		assertTrue(player.toString().equals("me: Current Hand: 3 A "));
	}

	@Test
	public void testToStringShowingTopCardOnly()
	{
		assertTrue(player.toStringShowingTopCardOnly().equals(
				"me: Current Hand: "));

		player.dealCard(new Card(3));
		player.dealCard(new Card(1));

		assertTrue(player.toStringShowingTopCardOnly().equals(
				"me: Current Hand: 3 X "));
	}

}
