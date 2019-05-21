package main;

import java.util.List;

import players.Player;
import utils.Game;
import utils.PartyGame;

public class Main {

    public static void main(String[] args) {
        
        List<Player> players = Game.setupGame();
        PartyGame.playGame(players);
    }
}
