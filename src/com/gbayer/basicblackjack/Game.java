/**
 * Project: BasicBlackJack
 * Package: com.gbayer.basicblackjack
 * File: Game.java
 * Author: Greg Bayer <greg@gbayer.com>
 * Date: Jul 19, 2010
 */

package com.gbayer.basicblackjack;

import org.apache.log4j.Logger;

import com.gbayer.basicblackjack.Player.Action;
import com.gbayer.basicblackjack.ui.ConsoleUI;
import com.gbayer.basicblackjack.ui.UI;

/**
 * The driver class for the BasicBackJack game.
 */
public class Game
{
	/** The Log4J logger. */
	private static Logger log = Logger.getLogger(Game.class);

	/** Constant - default number of starting chips */
	private static final int STARTING_CHIPS = 500;

	/** The user interface */
	private UI ui;

	/** The computer controlled dealer. */
	private ComputerDealer dealer;

	/** The human controlled player. */
	private HumanPlayer player;

	/** The deck in use for this game. */
	private Deck deck;

	/**
	 * Instantiates a new game with default starting chips (500).
	 */
	public Game()
	{
		this(STARTING_CHIPS);
	}

	/**
	 * Instantiates a new game.
	 * 
	 * @param startingChips
	 *            the number of starting chips
	 */
	public Game(int startingChips)
	{
		ui = new ConsoleUI();
		deck = new Deck();
		dealer = new ComputerDealer("Dealer");
		player = new HumanPlayer("Player", startingChips);
	}

	/**
	 * Execute steps to play game. Loop until until player out of chips or
	 * quits.
	 */
	public void play()
	{
		try
		{
			// Note: Using the term "round" instead of "hand" to avoid confusion
			// with the term "hand" meaning the cards currently held by a user
			int roundNumber = 0;
			// Loop over rounds of play until player is out of chips or quits
			while (player.getChipCount() > 0)
			{
				roundNumber++;
				log.info("Starting round " + roundNumber);

				ui.takeBet(player);
				dealCards();

				ui.playerStatus(player);
				ui.playerStatusWithCoveredCards(dealer);
				int playerOutcome = handleActions(player);
				ui.playerStatus(dealer);
				int dealerOutcome = handleActions(dealer);
				endRound(playerOutcome, dealerOutcome);
			}
		} catch (GameQuitException e)
		{
			log.info("Quitting");
			quitGame();
		}
	}

	/**
	 * Deal cards around the table (to player and dealer).
	 */
	private void dealCards()
	{
		log.info("Dealing cards");

		checkForLowDeck();

		player.newHand();
		dealer.newHand();

		// First card
		player.dealCard(deck.removeCard());
		dealer.dealCard(deck.removeCard());

		// Second card
		player.dealCard(deck.removeCard());
		dealer.dealCard(deck.removeCard());
	}

	/**
	 * Check for low deck. If deck has less than 20 cards remaining, bring in a
	 * new deck.
	 */
	private void checkForLowDeck()
	{
		if (deck.numCardsRemaining() < 20)
		{
			log.info("Deck low - bringing in new deck");
			deck.newDeck();
		}
	}

	/**
	 * Process player actions. Update hand accordingly.
	 * 
	 * @param currentPlayer
	 *            the current player
	 * @return the int
	 */
	private int handleActions(Player currentPlayer)
	{
		// loop until Stay or Bust
		boolean actionsPending = true;
		while (actionsPending)
		{
			Action action = currentPlayer.takeAction(ui);

			log.info("Handling " + action + " for " + currentPlayer.getName());

			switch (action)
			{
			case Hit:
				currentPlayer.dealCard(deck.removeCard());
				break;
			case Stay:
				actionsPending = false;
				break;
			default:
			}

			ui.playerActionTaken(currentPlayer, action);

			// if action changed cards, display new hand
			if (action != Action.Stay)
				ui.playerStatus(currentPlayer);

			currentPlayer.getHand().getTotalHandValue();
			if (currentPlayer.getHand().getTotalHandValue() > Hand.MAX_HAND_VALUE)
			{
				log.info(currentPlayer + " busts");

				ui.playerActionTaken(currentPlayer, Action.Bust);
				actionsPending = false;
			}
		}

		return currentPlayer.getHand().getTotalHandValue();
	}

	/**
	 * End round. Determine and apply result.
	 * 
	 * @param playerOutcome
	 *            the player outcome
	 * @param dealerOutcome
	 *            the dealer outcome
	 */
	private void endRound(int playerOutcome, int dealerOutcome)
	{
		switch (Game.assessHandWinner(playerOutcome, dealerOutcome))
		{
		case PlayerWins:
			log.info("Player wins round");
			ui.playerWinsRound();
			player.winBet();
			break;
		case DealerWins:
			log.info("Dealer wins round");
			ui.dealerWinsRound();
			player.looseBet();
			break;
		case Push:
			log.info("Round is a push");
			ui.roundIsPush();
			break;
		default:
		}

		ui.endRound();
	}

	/**
	 * Assess hand winner.
	 * 
	 * @param playerOutcome
	 *            the player outcome
	 * @param dealerOutcome
	 *            the dealer outcome
	 * @return the hand. result
	 */
	private static Hand.Result assessHandWinner(int playerOutcome,
			int dealerOutcome)
	{
		if (playerOutcome > Hand.MAX_HAND_VALUE
				&& dealerOutcome > Hand.MAX_HAND_VALUE)
			return Hand.Result.Push;
		if (playerOutcome > Hand.MAX_HAND_VALUE)
			return Hand.Result.DealerWins;
		if (dealerOutcome > Hand.MAX_HAND_VALUE)
			return Hand.Result.PlayerWins;

		if (playerOutcome > dealerOutcome)
			return Hand.Result.PlayerWins;
		else if (playerOutcome < dealerOutcome)
			return Hand.Result.DealerWins;
		else
			return Hand.Result.Push;
	}

	/**
	 * Quit game.
	 */
	private void quitGame()
	{
		ui.quitMessage();
	}

	/**
	 * Gets the user interface.
	 * 
	 * @return the uI
	 */
	private UI getUI()
	{
		return ui;
	}

	/**
	 * Gets the deck.
	 * 
	 * @return the deck
	 */
	public Deck getDeck()
	{
		return deck;
	}

	/**
	 * // BlackJack game starts here. Run this to play!
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args)
	{
		Game currentGame = new Game();
		UI ui = currentGame.getUI();
		ui.introMsg();
		currentGame.play();
		ui.exitMsg();
	}

}
