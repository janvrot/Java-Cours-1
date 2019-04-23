package main;

import main.enums.TypeAction;

import java.util.List;

public class Actions {

    public void doBoost(Player currentPlayer, int value, String stat) {
        switch (stat) {
            case "Life":
                currentPlayer.setLife(currentPlayer.getLife() + value);
                break;
            case "Strength":
                currentPlayer.setStrength(currentPlayer.getStrength() + value);
                break;
            case "Agility":
                currentPlayer.setAgility(currentPlayer.getAgility() + value);
                break;
            case "Intelligence":
                currentPlayer.setIntelligence(currentPlayer.getIntelligence() + value);
                break;
        }
    }
}
