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

import players.Guerrier;
import players.Player;
import players.Rodeur;

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
        System.out.println(buildMessage("Création de personnage du Joueur ", elements));
    }

    public static void chooseStatMessage(Player player) {
        elements.clear();
        elements.add(player.getLvl());
        System.out.println(buildMessage("Ajoutez vos statistiques, le montant total ne peut pas dépasser : ", elements));
    }

    public static void leftSkillPointsMessage(int points) {
        elements.clear();
        if (points > 0) {
            elements.add(points);
            elements.add(" points à dépenser !");
            System.out.println(buildMessage("Il te reste ", elements));
        } else {
            System.out.println("Tu as attribué tous tes points !");
        }
    }

    public static void playerTurn(Player player) {
        elements.clear();
        elements.add(player.getName());
        elements.add(" (" + player.getLife() + " pv)");
        System.out.println(buildMessage("Tour du ", elements));
    }

    public static String chooseAttack(Player player) {
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

    private static String buildMessage(String baseMessage, List<Object> elements) {
        StringBuilder sb = new StringBuilder(baseMessage);
        for (Object element : elements) {
            sb.append(element);
        }
        return sb.toString();
    }
}

