/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack.ui
 * File: UI.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */

package com.gbayer.basicblackjack.ui;

import com.gbayer.basicblackjack.GameQuitException;
import com.gbayer.basicblackjack.HumanPlayer;
import com.gbayer.basicblackjack.Player;
import com.gbayer.basicblackjack.Player.Action;

/**
 * An abstraction of the BasicBlackJack user interface.
 */
public interface UI
{

	/**
	 * Get user to place a bet.
	 * 
	 * @param currentPlayer
	 *            the current player
	 * @throws GameQuitException
	 *             the game quit exception
	 */
	public abstract void takeBet(HumanPlayer currentPlayer)
			throws GameQuitException;

	/**
	 * Gets the user's input.
	 * 
	 * @return the user input
	 */
	public abstract String getUserInput();

	/**
	 * Update status of players hand.
	 * 
	 * @param currentPlayer
	 *            the current player
	 */
	public abstract void playerStatus(Player currentPlayer);

	/**
	 * Update status of players hand. Keep all but the first card covered.
	 * 
	 * @param currentPlayer
	 *            the current player
	 */
	public abstract void playerStatusWithCoveredCards(Player currentPlayer);

	/**
	 * Get user to choose an action.
	 * 
	 * @return the user action choice
	 */
	public abstract Action getUserActionChoice();

	/**
	 * Update UI to indicate that player wins the round.
	 */
	public abstract void playerWinsRound();

	/**
	 * Update UI to indicate that player wins the round
	 */
	public abstract void dealerWinsRound();

	/**
	 * Update UI to indicate that the round is a push.
	 */
	public abstract void roundIsPush();

	/**
	 * Update UI to indicate the action that was taken.
	 * 
	 * @param player
	 *            the player
	 * @param action
	 *            the action
	 */
	public abstract void playerActionTaken(Player player, Action action);

	/**
	 * Update UI to indicate that the hand has ended.
	 */
	public abstract void endRound();

	/**
	 * Quit message.
	 */
	public abstract void quitMessage();

	/**
	 * Intro msg.
	 */
	public abstract void introMsg();

	/**
	 * Exit msg.
	 */
	public abstract void exitMsg();

}