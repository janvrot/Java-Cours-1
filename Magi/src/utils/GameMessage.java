package utils;

import java.util.ArrayList;
import java.util.List;

import players.Guerrier;
import players.Player;
import players.Rodeur;

/**
 * Génère les messages customisés en fonction de l'état de la partie
 *
 * @author Antoine Janvrot
 * @version 21 mai 2019
 */
public class GameMessage {

	/**
	 * les éléments à ajouter au message
	 */
	private static List<Object> elements = new ArrayList<>();

	/**
	 * Message de création d'un joueur
	 *
	 * @param nbPlayer
	 *            la position du joueur
	 */
	public void chooseClassMessage(int nbPlayer) {
		elements.clear();
		elements.add(nbPlayer);
		System.out.println(buildMessage("Création de personnage du Joueur ", elements));
	}

	/**
	 * Message pour que le joueur sache quels sont les points de statistiques
	 * possibles
	 *
	 * @param player
	 *            Le joueur en cours de sélection
	 */
	void chooseStatMessage(Player player) {
		elements.clear();
		elements.add(player.getLvl());
		System.out.println(
				buildMessage("Ajoutez vos statistiques, le montant total ne peut pas dépasser : ", elements));
	}

	/**
	 * Message représentant les points de compétences restant à attribuer
	 *
	 * @param points
	 *            les points restants
	 */
	public void leftSkillPointsMessage(int points) {
		elements.clear();
		if (points > 0) {
			elements.add(points);
			elements.add(" points à dépenser !");
			System.out.println(buildMessage("Il te reste ", elements));
		} else {
			System.out.println("Tu as attribué tous tes points !");
		}
	}

	/**
	 * Message représentant le début du tour d'un joueur
	 *
	 * @param player
	 *            Le joueur dont c'est le tour de jouer
	 */
	public void playerTurn(Player player) {
		elements.clear();
		elements.add(player.getName());
		elements.add(" (" + player.getLife() + " pv)");
		System.out.println(buildMessage("Tour du ", elements));
	}

	/**
	 * Message représentant le choix des attaques d'un joueur
	 *
	 * @param player
	 *            Le joueur dont c'est le tour de jouer
	 * @return Le message formatté
	 */
	public String chooseAttack(Player player) {
		String message;
		if (player instanceof Guerrier) {
			elements.clear();
			elements.add("(1:Coup d’épée, 2:Coup de Rage)");
			message = buildMessage("Choisissez une attaque ", elements);
		} else if (player instanceof Rodeur) {
			elements.clear();
			elements.add("(1:Tir à l’Arc, 2:Concentration)");
			message = buildMessage("Choisissez une attaque ", elements);
		} else {
			elements.clear();
			elements.add("(1:Boule de Feu, 2:Soin)");
			message = buildMessage("Choisissez une attaque ", elements);
		}
		return message;
	}

	/**
	 * Crée un message personnalisé avec possibilité d'ajout de paramètres
	 *
	 * @param baseMessage
	 *            Le début du message
	 * @param elements
	 *            Les éléments à ajouter au message
	 * @return Le message construit
	 */
	public String buildMessage(String baseMessage, List<Object> elements) {
		StringBuilder sb = new StringBuilder(baseMessage);
		for (Object element : elements) {
			sb.append(element);
		}
		return sb.toString();
	}
	
	public List<Object> getElements() {
		return GameMessage.elements;
	}
}
