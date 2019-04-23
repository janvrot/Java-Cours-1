package main;

import java.util.List;

public class Game {

   private List<Player> players;

    public Game() {
    }

    public Game(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}

