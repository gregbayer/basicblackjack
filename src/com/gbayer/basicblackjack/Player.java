/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack
 * File: Player.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */
package com.gbayer.basicblackjack;

import org.apache.log4j.Logger;

import com.gbayer.basicblackjack.ui.UI;

/**
 * A <code>Player</code> has a name assigned, holds a <code>Hand</code> of <code>Cards</code>, and is capable of taking actions.
 */
public abstract class Player
{

	/** The Log4J logger. */
	private static Logger log = Logger.getLogger(Player.class);

	/** The name assigned to the player. */
	protected String name;

	/** The player's current hand. */
	protected Hand hand;

	/**
	 * Action a player can take (or be forced to take).
	 */
	public enum Action
	{
		Hit, Stay, Bust
	}

	/**
	 * Instantiates a new player.
	 * 
	 * @param name
	 *            the name
	 */
	public Player(String name)
	{
		this.name = name;
		hand = new Hand();
	}

	/**
	 * Clear all cards in hand. Hand will be empty. Underlying data structure is
	 * reused.
	 */
	public void newHand()
	{
		log.debug("New hand");
		hand.clear();
	}

	/**
	 * Deal card to player. Card is placed in hand.
	 * 
	 * @param card
	 *            the card
	 */
	public void dealCard(Card card)
	{
		log.debug("Adding card to hand: " + card);
		hand.addCard(card);
	}

	/**
	 * Gets the player's assigned name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the player's hand.
	 * 
	 * @return the hand
	 */
	public Hand getHand()
	{
		return hand;
	}

	/**
	 * Generates a string containing player's assigned name concatenated with a
	 * string representing the player's hand.
	 * 
	 * @return the string
	 */
	public String toString()
	{
		return name + ": Current Hand: " + hand;
	}

	/**
	 * Generates a string containing player's assigned name concatenated with a
	 * string representing the player's hand with all but the first card
	 * face-down.
	 * 
	 * @return the string
	 */
	public String toStringShowingTopCardOnly()
	{
		return name + ": Current Hand: " + hand.toStringShowingTopCardOnly();
	}

	/**
	 * Player takes one or more permitted actions.
	 * 
	 * @param ui
	 *            the user interface
	 * @return Action action taken by player
	 */
	public abstract Action takeAction(UI ui);

}
