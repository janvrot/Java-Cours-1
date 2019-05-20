/**
 * <p>
 * Copyright © 2019 THALES Communications, France. All rights reserved.
 * </p>
 * <p>
 * Ce document est la propriété de THALES Communications, France,
 * il ne peut être ni reproduit, ni utilisé, ni communiqué, ni distribué
 * à  des tiers sans son autorisation préalable.
 * </p>
 * <p>
 * Créé le 20 mai 2019.
 * </p>
 */
package utils;

import java.util.List;

import players.Player;

public class PartyGame {
	
	private PartyGame() {
		
	}
	
	public List<Player> oneTurn(List<Player> players) {
		List<Player> allPlayers = players;
		for (Player player : allPlayers) {
			//chooseAttack
			//doAction
		}
		return allPlayers;
	}
}

