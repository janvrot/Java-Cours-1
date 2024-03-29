package utils;

import static enums.TypeFilter.CHOOSECLASS;
import static enums.TypeFilter.CHOOSELVL;
import static enums.TypeFilter.CHOOSEPLAYERS;
import static enums.TypeFilter.CHOOSESTATS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import players.Guerrier;
import players.Mage;
import players.Player;
import players.Rodeur;
import service.GameService;
import service.SetupService;

/**
 * Mise en place de la partie
 *
 * @author Antoine Janvrot
 * @version 16 juil. 2019
 */
public class SetupServiceImpl implements SetupService {

	private static GameService gameService = new GameServiceImpl();
	private static GameMessage gameMessage = new GameMessage();
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Player> setupGame() {
		int nbPlayers = gameService.addQuestion("Choisissez le nombre de joueurs (entre 2 et 10)", CHOOSEPLAYERS);
		List<Player> players = new ArrayList<>(nbPlayers);
		for (int i = 0; i < nbPlayers; i++) {
			gameMessage.chooseClassMessage(i+1);
			Player player = chooseClass();
			player.setName("Joueur " + (i + 1));
			chooseStat(player);
			players.add(player);
			System.out.println(player);
		}
		System.out.println("\n\nDEBUT DE LA PARTIE\n\n");
		return players;
	}
	
	/**
	 * Sélection de la classe d'un joueur
	 *
	 * @return
	 * 		Le joueur avec sa classe
	 */
	public Player chooseClass() {
		int playerType = gameService.addQuestion("Choisissez une classe (1:Guerrier, 2:Rodeur, 3:Mage)", CHOOSECLASS);
		switch (playerType) {
		case 1:
			return new Guerrier();
		case 2:
			return new Rodeur();
		case 3:
			return new Mage();
		default:
			return null;
		}
	}

	/**
	 * Application des statistiques au joueur
	 *
	 * @param player
	 * 		Le joueur en cours de création
	 * @return
	 * 		le joueur avec ses statistiques
	 */
	public Player chooseStat(Player player) {
		int lvlStat = gameService.addQuestion("Choisissez votre niveau (entre 1 et 100)", CHOOSELVL);
		player.setLvl(lvlStat);
		player.setLife(player.getLvl() * 5);
		List<Integer> points = skillPointLeft(player);
		player.setStrength(points.get(0));
		player.setIntelligence(points.get(1));
		player.setAgility(points.get(2));
		return player;
	}

	/**
	 * Vérifie qu'il reste des points à attribuer au joueur
	 *
	 * @param player
	 * 		le joueur en cours de choix de ses points de compétence
	 * @return
	 * 		le joueur avec les modifications appliquées
	 */
	public List<Integer> skillPointLeft(Player player) {
		gameMessage.chooseStatMessage(player);
		List<String> questions = Arrays.asList("Choisissez votre force", "Choisissez votre intelligence",
				"Choisisez votre Agilité");
		List<Integer> values = Arrays.asList(0, 0, 0);
		int pointsLeft = player.getLvl();
		while (pointsLeft > 0) {
			for (int i = 0; i < questions.size(); i++) {
				if (pointsLeft != 0) {
					int points = gameService.addQuestion(questions.get(i), CHOOSESTATS);
					if (points <= pointsLeft) {
						values.set(i, values.get(i) + points);
						pointsLeft = pointsLeft - points;
						gameMessage.leftSkillPointsMessage(pointsLeft);
					} else {
						System.out.println("Tu n'as pas assez de points restants");
						i--;
					}
				}
			}
		}
		return values;
	}
	
	public static void setGameService(GameService gameService) {
		SetupServiceImpl.gameService = gameService;
	}
}

