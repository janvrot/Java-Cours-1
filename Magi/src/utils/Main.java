package utils;

import java.util.List;

import enums.TypeFilter;
import players.Player;

public class Main {

    public static void main(String[] args) {
        int response = 1;
        while (response == 1) {
            List<Player> players = Setup.setupGame();
            PartyGame.playGame(players);
            response = Game.addQuestion("Voulez-vous refaire une partie ? 1:OUI, 2:NON", TypeFilter.CHOOSEATACK);
        }
        System.out.println("Merci d'avoir joué !! :)");
    }
}
