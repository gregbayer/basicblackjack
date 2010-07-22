/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack.ui
 * File: ConsoleUI.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */

package com.gbayer.basicblackjack.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.gbayer.basicblackjack.Game;
import com.gbayer.basicblackjack.GameQuitException;
import com.gbayer.basicblackjack.HumanPlayer;
import com.gbayer.basicblackjack.Player;
import com.gbayer.basicblackjack.Player.Action;

/**
 * An implementation of the UI interface for interacting with the user via stdin
 * and stdout.
 */
public class ConsoleUI implements UI
{

	/** The Log4J logger. */
	private static Logger log = Logger.getLogger(Player.class);

	/** The console input stream. */
	private InputStream in;

	/** The console output stream. */
	private PrintStream out;

	/**
	 * Constant - The amount a user should bet to indicate a desire to stop
	 * playing.
	 */
	public static final int QUIT_INDICATOR_BET = 0;

	/** Constant - Key to select hit action. */
	public static final int HIT_ACTION_KEY = 1;

	/** Constant - Key to select stay action. */
	public static final int STAY_ACTION_KEY = 2;

	/** Constant - Key to select first action. */
	public static final int MIN_ACTION_KEY = HIT_ACTION_KEY;

	/** Constant - Key to select last action. */
	public static final int MAX_ACTION_KEY = STAY_ACTION_KEY;

	/** Constant - Flag to indicate initial action request. */
	public static final int INITIAL_ACTION_FLAG = -1;

	/**
	 * Instantiates a new console UI reading from stdin and writing to stdout.
	 */
	public ConsoleUI()
	{
		this(System.in, System.out);
	}

	/**
	 * Instantiates a new console UI.
	 * 
	 * @param out
	 *            the out
	 */
	public ConsoleUI(InputStream in, PrintStream out)
	{
		this.in = in;
		this.out = out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gbayer.basicblackjack.UI#takeBet(com.gbayer.basicblackjack.HumanPlayer
	 * )
	 */
	@Override
	public void takeBet(HumanPlayer currentPlayer) throws GameQuitException
	{
		log.info("Taking bet from " + currentPlayer.getName());
		int bet = HumanPlayer.INVALID_BET_FLAG;
		while (bet < Game.MIN_BET || bet > Game.MAX_BET)
		{
			// If we made it in the loop without bet of -1, we had an invalid
			// bet last time
			if (bet != HumanPlayer.INVALID_BET_FLAG)
				log.debug("Last bet was invalid");

			try
			{
				out.println("You have " + currentPlayer.getChipCount()
						+ " chips.  Max bet "+Game.MAX_BET+" per hand.");
				out.println("How many chips would you like to bet? (bet "+QUIT_INDICATOR_BET+" to leave BlackJack table)");
				String betString = getUserInput();

				bet = Integer.parseInt(betString);

				if (bet == QUIT_INDICATOR_BET)
					throw new GameQuitException();

				currentPlayer.betChips(bet);
			} catch (NumberFormatException e)
			{
				// try again
				log.debug("Invalid bet format");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#deplayUserActions()
	 */
	@Override
	public Action getUserActionChoice()
	{
		log.info("Asking user to choose action to take");
		int choice = INITIAL_ACTION_FLAG;
		while (choice < MIN_ACTION_KEY || choice > MAX_ACTION_KEY)
		{
			// If we made it in the loop with a choice of something other than
			// INITIAL_ACTION_FLAG, the user made an invalid choice last time.
			if (choice != INITIAL_ACTION_FLAG)
				log.debug("Last choice was invalid");

			try
			{
				out.println("Options: (" + HIT_ACTION_KEY + ") Hit or ("
						+ STAY_ACTION_KEY + ") Stay? ");
				String betString = getUserInput();

				choice = Integer.parseInt(betString);
			} catch (NumberFormatException e)
			{
				// try again
				log.debug("Invalid choice format");
			}
		}

		log.info("User chooses " + choice);

		switch (choice)
		{
		case HIT_ACTION_KEY:
			return Action.Hit;
		case STAY_ACTION_KEY:
			return Action.Stay;
		default:
			log.error("Invalid action chosen");
			return Action.Stay; // this shouldn't happen
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#getUserInput()
	 */
	@Override
	public String getUserInput()
	{
		InputStreamReader input = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(input);
		String inputString = "";
		try
		{
			inputString = reader.readLine();
		} catch (IOException e)
		{
			log.error("Error getting user input", e);
		}

		return inputString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gbayer.basicblackjack.UI#playerStatus(com.gbayer.basicblackjack.Player
	 * )
	 */
	@Override
	public void playerStatus(Player currentPlayer)
	{
		out.println(currentPlayer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gbayer.basicblackjack.UI#playerStatusWithCoveredCards(com.gbayer.
	 * basicblackjack.Player)
	 */
	@Override
	public void playerStatusWithCoveredCards(Player currentPlayer)
	{
		out.println(currentPlayer.toStringShowingTopCardOnly());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#playerWinsRound()
	 */
	@Override
	public void playerWinsRound()
	{
		out.println("Player wins the round.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#dealerWinsRound()
	 */
	@Override
	public void dealerWinsRound()
	{
		out.println("Dealer wins the round.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#roundIsPush()
	 */
	@Override
	public void roundIsPush()
	{
		out.println("This round is a push.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#roundIsPush()
	 */
	@Override
	public void playerActionTaken(Player player, Action action)
	{
		out.println(player.getName() + " " + action.toString() + "s.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#endRound()
	 */
	@Override
	public void endRound()
	{
		out.println("----------------\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#quitMessage()
	 */
	@Override
	public void quitMessage()
	{
		out.println("Leaving table to get a drink.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#introMsg()
	 */
	@Override
	public void introMsg()
	{
		out.println("Welcome to the BlackJack table.");
		out.println("Please have a seat.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbayer.basicblackjack.UI#exitMsg()
	 */
	@Override
	public void exitMsg()
	{
		out.println("Thanks for playing BlackJack.");
		out.println("Come again soon!");
	}
}
