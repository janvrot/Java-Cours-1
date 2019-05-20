package main;

import main.enums.TypeAction;

import java.util.List;

public class Actions {

    public void doBoost(Player targetPlayer, int value, String stat) {
        switch (stat) {
            case "Life":
                targetPlayer.setLife(targetPlayer.getLife() + value);
                break;
            case "Strength":
                targetPlayer.setStrength(targetPlayer.getStrength() + value);
                break;
            case "Agility":
                targetPlayer.setAgility(targetPlayer.getAgility() + value);
                break;
            case "Intelligence":
                targetPlayer.setIntelligence(targetPlayer.getIntelligence() + value);
                break;
                
        }
    }
}
