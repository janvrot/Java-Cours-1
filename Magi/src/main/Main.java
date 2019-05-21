package main;

import java.util.List;

import main.enums.TypeFilter;
import players.Player;
import utils.Game;
import utils.PartyGame;

public class Main {

    public static void main(String[] args) {
        int response = 1;
        while (response == 1) {
            List<Player> players = Game.setupGame();
            PartyGame.playGame(players);
            response = Game.addQuestion("Voulez-vous refaire une partie ? 1:OUI, 2:NON", TypeFilter.CHOOSEATACK);
        }
        System.out.println("Merci d'avoir joué !! :)");
    }
}
