/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack
 * File: ComputerDealer.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */
package com.gbayer.basicblackjack;

import org.apache.log4j.Logger;

import com.gbayer.basicblackjack.ui.UI;

/**
 * <code>Player</code> that follows BlackJack dealer decision rules and does not
 * interact with UI.
 */
public class ComputerDealer extends Player
{

	/** The Log4J logger. */
	private static Logger log = Logger.getLogger(ComputerDealer.class);

	/** Constant - hand value at which dealer must Stay. */
	public static final int STAY_VALUE = 17;

	/**
	 * Instantiates a new computer dealer.
	 * 
	 * @param name
	 *            the name assigned to this player
	 */
	public ComputerDealer(String name)
	{
		super(name);
	}

	/**
	 * Dealer takes action based on current hand value and dealer rules.
	 * 
	 * @return action taken
	 */
	@Override
	public Action takeAction(UI ui)
	{
		int handValue = hand.getTotalHandValue();

		log.info("ComputerDealer takes action on hand value: " + handValue);

		if (handValue > Hand.MAX_HAND_VALUE)
			return Action.Bust;
		if (handValue >= STAY_VALUE)
			return Action.Stay;
		else
			return Action.Hit;
	}

}
