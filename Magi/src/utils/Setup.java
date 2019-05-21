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

import static main.enums.TypeFilter.*;
import static utils.Game.addQuestion;

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
		int playerType = addQuestion("Choisissez une classe (1:Guerrier, 2:Rodeur, 3:Mage)", CHOOSECLASS);
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
		int lvlStat = addQuestion("Choisissez votre niveau (entre 1 et 100)", CHOOSELVL);
		player.setLvl(lvlStat);
		player.setLife(player.getLvl() * 5);
		List<Integer> points = skillPointLeft(player);
		player.setStrength(points.get(0));
		player.setIntelligence(points.get(1));
		player.setAgility(points.get(2));
		return player;
	}

	public static List<Integer> skillPointLeft(Player player) {
		GameMessage.chooseStatMessage(player);
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
						GameMessage.leftSkillPointsMessage(pointsLeft);
					} else {
						System.out.println("Tu n'as pas assez de points restants");
						i--;
					}
				}
			}
		}
		return values;
	}
}

