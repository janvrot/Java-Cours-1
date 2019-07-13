package utils;

import players.Player;

import static enums.TypeFilter.CHOOSEATACK;
import static enums.TypeFilter.CHOOSETARGET;
import static enums.TypeKeys.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import enums.TypeAction;
import enums.TypeKeys;
import enums.TypeStat;
import enums.TypeTarget;

public class PartyGame {

	private PartyGame() {

	}

	public static void playGame(List<Player> players) {
		List<Player> allPlayers = players;
		Player player = null;
		while (allPlayers.size() > 1) {
			for (int i = 0; i < players.size(); i++) {
				if (!checkLastPlayer(player, players.get(i))) {
					player = players.get(i);
					GameMessage.playerTurn(player);
					int chooseAttack = Game.addQuestion(GameMessage.chooseAttack(player), CHOOSEATACK);
					List<Map<TypeKeys, Object>> attack = doAttack(chooseAttack, player);
					for (Map<TypeKeys, Object> infos : attack) {
						int targetPosition = checkTarget(allPlayers, player, (TypeTarget) infos.get(TARGET));
						changeStat(allPlayers, targetPosition, (Integer) infos.get(AMOUNT),
								(TypeAction) infos.get(ACTION), (TypeStat) infos.get(STAT));
					}
				}
			}
		}
		if (allPlayers.size() == 1) {
			System.out.println(allPlayers.get(0).getName() + " a gagné !!!");
		} else {
			System.out.println("Partie terminée sans vainqueur !!");
		}
	}

	public static boolean checkLastPlayer(Player lastPlayer, Player currentPlayer) {
		if (lastPlayer != null)
			return lastPlayer.getName() == currentPlayer.getName();
		else
			return false;
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
				target = Game.addQuestion("Qui est votre cible ?", CHOOSETARGET) - 1;
			}
			return target;
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
			System.out.println(players.get(targetPosition).getName() + " est mort !!!!");
			for (int i = targetPosition; i < players.size() - 1; i++) {
				players.set(i, players.get(i + 1));
			}
			players.remove(players.size() - 1);
		}
		return players;
	}

	public static List<Player> removeDeadPlayer(List<Player> players) {
		for (Iterator<Player> iterator = players.iterator(); iterator.hasNext();) {
			Player player = iterator.next();
			if (player.getLife() <= 0) {
				iterator.remove();
			}
		}
		return players;
	}

	public static Player doAction(TypeAction action, Player player, int amount, TypeStat stat) {
		if (action == TypeAction.DAMAGE) {
			player.setLife(player.getLife() - minimalValue(amount));
			System.out.println(player.getName() + " a perdu " + minimalValue(amount) + " points de vie");
		} else {
			doBoost(player, amount, stat);
		}
		return player;
	}

	public static Player doBoost(Player player, int amount, TypeStat stat) {
		switch (stat) {
		case LIFE:
			player.setLife(player.getLife() + minimalValue(amount));
			if (player.getLife() > player.getLvl() * 5) {
				player.setLife(player.getLvl() * 5);
			}
			System.out.println(player.getName() + " a été soigné de " + minimalValue(amount) + " points de vie");
			break;
		case AGILITY:
			player.setAgility(player.getAgility() + minimalValue(amount));
			System.out.println(player.getName() + " a gagné " + minimalValue(amount) + " points d'agilité");
			break;
		case INTELLIGENCE:
			player.setIntelligence(player.getIntelligence() + minimalValue(amount));
			System.out.println(player.getName() + " a gagné " + minimalValue(amount) + " points d'intelligence");
			break;
		case STRENGTH:
			player.setStrength(player.getStrength() + minimalValue(amount));
			System.out.println(player.getName() + " a gagné " + minimalValue(amount) + " points de force");
			break;
		}
		return player;
	}

	public static int minimalValue(int amount) {
		if (amount == 0)
			return 1;
		else
			return amount;
	}
}
