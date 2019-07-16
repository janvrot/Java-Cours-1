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
 * Créé le 4 juil. 2019.
 * </p>
 */
package service;

import java.util.List;

import players.Player;

/**
 * Interface de mise en place de la partie
 *
 * @author Antoine Janvrot
 * @version 16 juil. 2019
 */
public interface SetupService {

	/**
	 * Création des joueurs
	 *
	 * @return
	 * 		Les joueurs de la partie
	 */
	List<Player> setupGame();
}

