package com.gbayer.basicblackjack;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gbayer.basicblackjack.Player.Action;

public class ComputerDealerTest
{
	private ComputerDealer hit = new ComputerDealer("hit");
	private ComputerDealer stay;

	@Before
	public void setUp() throws Exception
	{
		hit = new ComputerDealer("hit");
		hit.dealCard(new Card(7));
		hit.dealCard(new Card(9));
		stay = new ComputerDealer("stay");
		stay.dealCard(new Card(10));
		stay.dealCard(new Card(11));
	}

	@Test
	public void testTakeAction()
	{
		assertTrue(hit.getName().equals("hit"));
		assertTrue(hit.getHand().getTotalHandValue() == 16);

		assertTrue(hit.takeAction(null) == Action.Hit);
		assertTrue(stay.takeAction(null) == Action.Stay);
	}

}
