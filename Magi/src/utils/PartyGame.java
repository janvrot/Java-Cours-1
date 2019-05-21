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

import main.enums.TypeAction;
import main.enums.TypeKeys;
import main.enums.TypeStat;
import main.enums.TypeTarget;
import players.Player;

import java.util.List;
import java.util.Map;

import static main.enums.TypeFilter.CHOOSEATACK;
import static main.enums.TypeFilter.CHOOSETARGET;
import static main.enums.TypeKeys.*;

public class PartyGame {

	private PartyGame() {

	}

	public static void playGame(List<Player> players) {
		List<Player> allPlayers = players;
		while (allPlayers.size() > 1) {
			for (Player player : allPlayers) {
				int chooseAttack = Game.addQuestion("Choisissez une attaque", CHOOSEATACK);
				List<Map<TypeKeys, Object>> attack = doAttack(chooseAttack, player);
				for (Map<TypeKeys, Object> infos : attack) {
					int targetPosition = checkTarget(allPlayers, player, (TypeTarget) infos.get(TARGET));
					changeStat(allPlayers, targetPosition, (Integer) infos.get(AMOUNT),
							(TypeAction) infos.get(ACTION), (TypeStat) infos.get(STAT));
				}
			}
		}
		System.out.println("Partie terminée !!");
	}

	public static List<Map<TypeKeys, Object>> doAttack(int result, Player player) {
		List<Map<TypeKeys, Object>> attack;
		if (result == 1) {
			attack = player.basicAttack();
		} else {
			attack = player.specialAttack();
		}
		return attack;
	}

	public static int checkTarget(List<Player> allPlayers, Player player, TypeTarget target) {
		switch (target) {
		case MYSELF:
			return findMe(allPlayers, player);
		case OTHER:
			return findOther(allPlayers, player);
		default:
			return -1;
		}
	}

	public static int findMe(List<Player> allPlayers, Player player) {
		int result = 0;
		for (int i = 0; i < allPlayers.size(); i++) {
			if (allPlayers.get(i) == player) {
				result = i;
			}
		}
		return result;
	}

	public static int findOther(List<Player> allPlayers, Player player) {
		if (allPlayers.size() == 2) {
			int result = 0;
			for (int i = 0; i < allPlayers.size(); i++) {
				if (allPlayers.get(i) != player) {
					result = i;
				}
			}
			return result;
		} else {
			int target = Game.addQuestion("Qui est votre cible ?", CHOOSETARGET) - 1;
			while (!verifyTarget(allPlayers, player, target)) {
				System.out.println("Cible invalide !");
				target = Game.addQuestion("Qui est votre cible ?", CHOOSETARGET);
			}
			return target ;
		}
	}

	public static boolean verifyTarget(List<Player> allPlayers, Player player, int target) {
		try {
			return (allPlayers.get(target) != player);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	public static List<Player> changeStat(List<Player> players, int targetPosition, int amount, TypeAction action,
			TypeStat stat) {

		Player updatePlayer = doAction(action, players.get(targetPosition), amount, stat);
		if (updatePlayer.getLife() > 0) {
			players.set(targetPosition, updatePlayer);
		} else {
			players.remove(targetPosition);
		}
		return players;
	}

	public static Player doAction(TypeAction action, Player player, int amount, TypeStat stat) {
		if (action == TypeAction.DAMAGE) {
			player.setLife(player.getLife() - amount);
		} else {
			doBoost(player, amount, stat);
		}
		return player;
	}

	public static Player doBoost(Player player, int amount, TypeStat stat) {
		switch (stat) {
		case LIFE:
			player.setLife(player.getLife() + amount);
			if (player.getLife() > player.getLvl() * 5) {
				player.setLife(player.getLvl() * 5);
			}
			break;
		case AGILITY:
			player.setAgility(player.getAgility() + amount);
			break;
		case INTELLIGENCE:
			player.setIntelligence(player.getIntelligence() + amount);
			break;
		case STRENGTH:
			player.setStrength(player.getStrength() + amount);
			break;
		}
		return player;
	}
}
