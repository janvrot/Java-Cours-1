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
 * Créé le 17 mai 2019.
 * </p>
 */
package utils;

import main.enums.TypeFilter;

public final class PlayerConditions {

    private PlayerConditions() {

    }

    public static boolean chooseClassCondition(int result) {
        return (result > 0 && result < 4);
    }

    public static boolean choosePlayers(int result) {
        return result > 1;
    }

    public static boolean chooseLvl(int result) {
        return (result > 0 && result <= 100);
    }

    public static boolean chooseStats(int result) {
        return (result >= 0 && result <= 100);
    }

    public static boolean chooseAttack(int result) {
        return (result > 0 && result < 3);
    }

    public static boolean chooseTarget(int result) {
        return (result > 0);
    }

    public static boolean addFilter(int response, TypeFilter filter) {
        if (filter != null && response != -1) {
            switch (filter) {
                case CHOOSEPLAYERS:
                    return choosePlayers(response);
                case CHOOSECLASS:
                    return chooseClassCondition(response);
                case CHOOSESTATS:
                    return chooseStats(response);
                case CHOOSEATACK:
                    return chooseAttack(response);
                case CHOOSETARGET:
                    return chooseTarget(response);
                case CHOOSELVL:
                    return chooseLvl(response);
                default:
                    return false;
            }
        } else {
            return response != -1;
        }
    }
}
