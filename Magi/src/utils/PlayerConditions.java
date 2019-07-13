package utils;

import enums.TypeFilter;

public final class PlayerConditions {

    public boolean chooseClassCondition(int result) {
        return (result > 0 && result < 4);
    }

    public boolean choosePlayers(int result) {
        return result > 1;
    }

    public boolean chooseLvl(int result) {
        return (result > 0 && result <= 100);
    }

    public boolean chooseStats(int result) {
        return (result >= 0 && result <= 100);
    }

    public boolean chooseAttack(int result) {
        return (result > 0 && result < 3);
    }

    public boolean chooseTarget(int result) {
        return (result > 0);
    }

    public boolean addFilter(int response, TypeFilter filter) {
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
