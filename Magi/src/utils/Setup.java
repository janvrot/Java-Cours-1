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

import static main.Game.addQuestion;
import static main.enums.TypeFilter.CHOOSECLASS;
import static main.enums.TypeFilter.CHOOSESTATS;

import java.util.Arrays;
import java.util.List;

import players.Guerrier;
import players.Mage;
import players.Player;
import players.Rodeur;

public class Setup {
	
	private Setup () {
		
	}
	
	public static Player chooseClass() {
		int playerType = addQuestion("Choisissez une classe :", CHOOSECLASS);
		switch (playerType) {
		case 1:
			return new Guerrier();
		case 2:
			return new Rodeur();
		case 3:
			return new Mage();
		default:
			return new Guerrier();
		}
	}

	public static Player chooseStat(Player player) {
		int lvlStat = addQuestion("Choisissez votre niveau : ", CHOOSESTATS);
		player.setLvl(lvlStat);
		player.setLife(player.getLvl() * 5);
		List<Integer> points = skillPointLeft(player);
		player.setStrength(points.get(0));
		player.setIntelligence(points.get(1));
		player.setAgility(points.get(2));
		return player;
	}

	public static List<Integer> skillPointLeft(Player player) {
		List<String> questions = Arrays.asList("Choisissez votre force", "Choisissez votre intelligence",
				"Choisisez votre Agilité");
		List<Integer> values = Arrays.asList(0, 0, 0);
		int pointsLeft = player.getLvl();
		while (pointsLeft > 0) {
			for (int i = 0; i < questions.size(); i++) {
				if (pointsLeft != 0) {
					int points = addQuestion(questions.get(i), CHOOSESTATS);
					if (points <= pointsLeft) {
						values.set(i, values.get(i) + points);
						pointsLeft = pointsLeft - points;
					} else {
						System.out.println("Tu n'as pas assez de points restants");
						i--;
					}
				}
			}
			if (pointsLeft > 0) {
				System.out.println("Il te reste des points à attribuer :)");
			} else {
				System.out.println("tu as attribué tous tes points");
			}
		}
		return values;
	}
}

