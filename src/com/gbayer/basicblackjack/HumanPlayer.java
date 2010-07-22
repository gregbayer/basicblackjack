/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack
 * File: HumanPlayer.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */
package com.gbayer.basicblackjack;

import org.apache.log4j.Logger;

import com.gbayer.basicblackjack.ui.UI;

/**
 * A human <code>Player</code> that takes action based on UI prompts.
 */
public class HumanPlayer extends Player
{

	/** The Log4J logger. */
	private static Logger log = Logger.getLogger(HumanPlayer.class);

	/** The player's current chip count. */
	private int chips;

	/** The player's current bet. */
	private int currentBet;

	/** Constant - Flag indicating that no bet has been placed. */
	public static final int INVALID_BET_FLAG = -1;

	/**
	 * Instantiates a new human player.
	 * 
	 * @param name
	 *            the player's name
	 * @param startingChips
	 *            number of starting chips
	 */
	public HumanPlayer(String name, int startingChips)
	{
		super(name);
		this.chips = startingChips;
		this.currentBet = INVALID_BET_FLAG; // no current bet
	}

	/**
	 * Human player takes action based on UI prompts.
	 * 
	 * @return action taken
	 */
	@Override
	public Action takeAction(UI ui)
	{
		return ui.getUserActionChoice();
	}

	/**
	 * Gets the player's current chip count.
	 * 
	 * @return the chip count
	 */
	public int getChipCount()
	{
		return chips;
	}

	/**
	 * Bet chips.
	 * 
	 * @param chips
	 *            the number of chips
	 */
	public void betChips(int chips)
	{
		this.currentBet = chips;
	}

	/**
	 * Player wins bet. Previously bet chips are add to the player's chip count.
	 * Note: Bet chips must be called first.
	 */
	public void winBet()
	{
		if (this.currentBet == INVALID_BET_FLAG)
			log.error("Player.winBet() called without bet placed.");
		else
		{
			log.info("Bet won: " + this.currentBet);
			winChips(this.currentBet);
			this.currentBet = INVALID_BET_FLAG; // reset currentBet
		}
	}

	/**
	 * Player looses bet. Previously bet chips are subtracted from the player's
	 * chip count. Note: Bet chips must be called first.
	 */
	public void looseBet()
	{
		if (this.currentBet == INVALID_BET_FLAG)
			log.error("Player.looseBet() called without bet placed.");
		else
		{
			log.info("Bet lost: " + this.currentBet);
			loseChips(this.currentBet);
			this.currentBet = INVALID_BET_FLAG; // reset currentBet
		}
	}

	/**
	 * Player wins chips.
	 * 
	 * @param chips
	 *            the chips
	 */
	private void winChips(int chips)
	{
		this.chips += chips;
	}

	/**
	 * Player loses chips.
	 * 
	 * @param chips
	 *            the chips
	 */
	private void loseChips(int chips)
	{
		this.chips -= chips;
	}

}
