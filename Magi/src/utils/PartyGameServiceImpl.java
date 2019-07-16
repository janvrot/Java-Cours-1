package utils;

import static enums.TypeFilter.CHOOSEATACK;
import static enums.TypeFilter.CHOOSETARGET;
import static enums.TypeKeys.ACTION;
import static enums.TypeKeys.AMOUNT;
import static enums.TypeKeys.STAT;
import static enums.TypeKeys.TARGET;

import java.util.List;
import java.util.Map;

import enums.TypeAction;
import enums.TypeKeys;
import enums.TypeStat;
import enums.TypeTarget;
import players.Player;
import service.GameService;
import service.PartyGameService;

/**
 * gestion de la partie
 *
 * @author Antoine Janvrot
 * @version 16 juil. 2019
 */
public class PartyGameServiceImpl implements PartyGameService {

	private static GameService gameService = new GameServiceImpl();
	private static GameMessage gameMessage = new GameMessage();

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Player> playGame(List<Player> players) {
		List<Player> allPlayers = players;
		Player player = null;
		while (allPlayers.size() > 1) {
			player = findNextPlayer(allPlayers, player);
			gameMessage.playerTurn(player);
			int chooseAttack = gameService.addQuestion(gameMessage.chooseAttack(player), CHOOSEATACK);
			List<Map<TypeKeys, Object>> attack = doAttack(chooseAttack, player);
			for (Map<TypeKeys, Object> infos : attack) {
				if (player.getLife() > 0) {
					int targetPosition = checkTarget(allPlayers, player, (TypeTarget) infos.get(TARGET));
					changeStat(allPlayers, targetPosition, (Integer) infos.get(AMOUNT),
							(TypeAction) infos.get(ACTION), (TypeStat) infos.get(STAT));
					removeDeadPlayer(allPlayers, targetPosition);
				}
			}
		}
		if (allPlayers.size() == 1) {
			System.out.println(allPlayers.get(0).getName() + " a gagné !!!");
			return allPlayers;
		} else {
			System.out.println("Partie terminée sans vainqueur !!");
			return allPlayers;
		}
	}	

	/**
	 * Récupère le prochain jopueur à devoir jouer
	 *
	 * @param players
	 * 		la liste des joueurs
	 * @param oldPlayer
	 * 		le dernier joueur à avoir joué
	 * @return
	 * 		le joueur qui va jouer
	 */
	public Player findNextPlayer(List<Player> players, Player oldPlayer) {
		Player finalPlayer = players.get(0);
		if (oldPlayer == null)
			return players.get(0);
		else if (players.get(players.size() - 1) == oldPlayer)
			return players.get(0);
		else {
			int oldNumber = splitName(oldPlayer);
			for (Player player : players) {
				if (splitName(player) > oldNumber) {
					finalPlayer = player;
					break;
				}			
			}
		}
		return finalPlayer;
	}

	/**
	 * Récupère la positiuon du joueur
	 *
	 * @param player
	 * 		le joueur courant
	 * @return
	 * 		la position du joueur
	 */
	public int splitName(Player player) {
		String[] playerNumber = player.getName().split(" ");
		return Integer.parseInt(playerNumber[1]);
	}

	/**
	 * Supprime le joueur mort
	 *
	 * @param players
	 * 		La liste des joueurs
	 * @param targetPosition
	 * 		la position du joueur en cours de vérification
	 * @return
	 * 		la liste des joueurs mise à jour
	 */
	public List<Player> removeDeadPlayer(List<Player> players, int targetPosition) {
		if (players.get(targetPosition).getLife() <= 0) {
			System.out.println(players.get(targetPosition).getName() + " est mort !!!!");
			for (int i = targetPosition; i < players.size() - 1; i++) {
				players.set(i, players.get(i + 1));
			}
			players.remove(players.size() - 1);
		}
		return players;
	}

	/**
	 * Attaque d'un joueur
	 *
	 * @param result
	 * 		l'attaque choisie
	 * @param player
	 * 		le joueur lançant l'attaque
	 * @return
	 * 		la liste des actions constituant une attaque
	 */
	public List<Map<TypeKeys, Object>> doAttack(int result, Player player) {
		List<Map<TypeKeys, Object>> attack;
		if (result == 1) {
			attack = player.basicAttack();
		} else {
			attack = player.specialAttack();
		}
		return attack;
	}

	/**
	 * Choix de la cible de l'attaque
	 *
	 * @param allPlayers
	 * 		Liste des joueurs
	 * @param player
	 * 		le joueur ciblé
	 * @param target
	 * 		le type de cible
	 * @return
	 * 		la position de la cible dans la liste des joueurs
	 */
	public int checkTarget(List<Player> allPlayers, Player player, TypeTarget target) {
		switch (target) {
		case MYSELF:
			return findMe(allPlayers, player);
		case OTHER:
			return findOther(allPlayers, player);
		default:
			return -1;
		}
	}

	/**
	 * Ciblage du joueur courant
	 *
	 * @param allPlayers
	 * 		la liste des joueurs
	 * @param player
	 * 		le joueur cible
	 * @return
	 * 		la position du joueur
	 */
	public int findMe(List<Player> allPlayers, Player player) {
		int result = 0;
		for (int i = 0; i < allPlayers.size(); i++) {
			if (allPlayers.get(i) == player) {
				result = i;
			}
		}
		return result;
	}

	/**
	 * Ciblage d'un autre joueur
	 *
	 * @param allPlayers
	 * 		La liste des joueurs
	 * @param player
	 * 		le joueur ciblé
	 * @return
	 * 		la pôsition du joueur
	 */
	public int findOther(List<Player> allPlayers, Player player) {
		if (allPlayers.size() == 2) {
			int result = 0;
			for (int i = 0; i < allPlayers.size(); i++) {
				if (allPlayers.get(i) != player) {
					result = i;
				}
			}
			return result;
		} else if (allPlayers.size() > 2) {
			int target = gameService.addQuestion("Qui est votre cible ?", CHOOSETARGET) - 1;
			while (!verifyTarget(allPlayers, player, target)) {
				System.out.println("Cible invalide !");
				target = gameService.addQuestion("Qui est votre cible ?", CHOOSETARGET) - 1;
			}
			return target;
		} else
			return 0;
	}

	/**
	 * Vérifie que la cible existe
	 *
	 * @param allPlayers
	 * 		la liste des joueurs
	 * @param player
	 * 		le joueur ciblé
	 * @param target
	 * 		la position du joueur présumé
	 * @return
	 * 		la validité du joueur
	 */
	public boolean verifyTarget(List<Player> allPlayers, Player player, int target) {
		try {
			return (allPlayers.get(target) != player);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Applique le changement des statistiques
	 *
	 * @param players
	 * 		la liste des joueurs
	 * @param targetPosition
	 * 		la position de la cible
	 * @param amount
	 * 		la valeur de modification
	 * @param action
	 * 		le type d'action
	 * @param stat
	 * 		la statistique touchée
	 * @return
	 * 		la liste des joueurs actualisée
	 */
	public List<Player> changeStat(List<Player> players, int targetPosition, int amount, TypeAction action,
			TypeStat stat) {

		Player updatePlayer = doAction(action, players.get(targetPosition), amount, stat);
		if (updatePlayer.getLife() > 0) {
			players.set(targetPosition, updatePlayer);
		}
		return players;
	}

	/**
	 * Vérification du type d'attaque
	 *
	 * @param action
	 * 		l'action choisie
	 * @param player
	 * 		le joueur touché
	 * @param amount
	 * 		la valeur de l'attaque
	 * @param stat
	 * 		la statistique touchée
	 * @return
	 * 		Le joueur mis à jour
	 */
	public Player doAction(TypeAction action, Player player, int amount, TypeStat stat) {
		if (action == TypeAction.DAMAGE) {
			player.setLife(player.getLife() - minimalValue(amount));
			System.out.println(player.getName() + " a perdu " + minimalValue(amount) + " points de vie");
		} else {
			doBoost(player, amount, stat);
		}
		return player;
	}

	/**
	 * Action de boost d'une statistique d'un joueur
	 *
	 * @param player
	 * 		le joueur ciblé
	 * @param amount
	 * 		la valeur du boost
	 * @param stat
	 * 		la statistique touchée
	 * @return
	 * 		Le joueur mis à jour
	 */
	public Player doBoost(Player player, int amount, TypeStat stat) {
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
		default:
			break;
		}
		return player;
	}

	/**
	 * Valeur minimale d'une action
	 *
	 * @param amount
	 * 		la valeur de base
	 * @return
	 * 		la valeur de l'action après traitement
	 */
	public int minimalValue(int amount) {
		if (amount == 0)
			return 1;
		else
			return amount;
	}

	public static void setGameService(GameService gameService) {
		PartyGameServiceImpl.gameService = gameService;
	}
}
