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

import main.Game;
import main.enums.TypeAction;
import main.enums.TypeKeys;
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

    public List<Player> oneTurn(List<Player> players) {
        List<Player> allPlayers = players;
        while (allPlayers.size() > 1) {
            for (Player player : allPlayers) {
                int attack = Game.addQuestion("Choisissez une attaque", CHOOSEATACK);
                //chooseAttack
                //selectTarget
                //doAction
                //doAmount
            }
        }
        return allPlayers;
    }

    public void doAttack(int result, Player player, List<Player> allPlayers) {
        List<Map<TypeKeys, Object>> attack;
        if (result == 1) {
            attack = player.basicAttack();
        } else {
            attack = player.specialAttack();
        }
        for (Map<TypeKeys, Object> infos : attack) {
            int amount = (Integer) infos.get(AMOUNT);
            TypeAction action = (TypeAction) infos.get(ACTION);
            TypeTarget target = (TypeTarget) infos.get(TARGET);
        }
    }

    public void doAction(int amount, TypeAction action, TypeTarget target, List<Player> allPlayers, Player player) {
        int targetPosition = checkTarget(allPlayers, player, target);
    }

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

    public int findMe(List<Player> allPlayers, Player player) {
        int result = 0;
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == player) {
                result = i;
            }
        }
        return result;
    }

    public int findOther(List<Player> allPlayers, Player player) {
        if (allPlayers.size() == 2) {
            int result = 0;
            for (int i = 0; i < allPlayers.size(); i++) {
                if (allPlayers.get(i) != player) {
                    result = i;
                }
            }
            return result;
        } else {
            int target = Game.addQuestion("Qui est votre cible", CHOOSETARGET);
            while (!verifyTarget(allPlayers, player, target)) {
                System.out.println("Paramètre invalide !");
                target = Game.addQuestion("Qui est votre cible", CHOOSETARGET);
            }
            return target;
        }
    }

    public boolean verifyTarget(List<Player> allPlayers, Player player, int target) {
        if (allPlayers.get(target) == null || allPlayers.get(target) == player) {
            return false;
        } else {
            return true;
        }
    }

    public List<Player> changeStat(List<Player> players, int targetPosition, int amount, TypeAction action) {

    }
}

