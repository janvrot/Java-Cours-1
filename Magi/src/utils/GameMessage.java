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
 * Créé le 21 mai 2019.
 * </p>
 */
/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.List;

import players.Player;

/**
 *
 * @author Antoine Janvrot
 * @version 21 mai 2019
 */
public class GameMessage {
	
	private static List<Object> elements = new ArrayList<>();

	public static void chooseClassMessage(int nbPlayer) {
		elements.clear();
		elements.add(nbPlayer);
		buildMessage("Création de personnage du Joueur ", elements);
	}
	
	public static void chooseStatMessage(Player player) {
		elements.clear();
		elements.add(player.getLvl());
		buildMessage("Ajoutez vos statistiques, le montant total ne peut pas dépasser : ", elements);
	}
	
	public static void leftSkillPointsMessage(int points) {
		elements.clear();
		if (points > 0) {
			elements.add(points);
			elements.add(" points à dépenser !");
			buildMessage("Il te reste ", elements);
		} else {
			System.out.println("Tu as attribué tous tes points !");
		}
	}
	
	public static void playerTurn(Player player) {
		elements.clear();
		elements.add(player.getName());
		buildMessage("Tour du ", elements);
	}
	
	private static void buildMessage(String baseMessage, List<Object> elements) {
		StringBuilder sb = new StringBuilder(baseMessage);
		for (Object element : elements) {
			sb.append(element);
		}
		System.out.println(sb);
	}
}

