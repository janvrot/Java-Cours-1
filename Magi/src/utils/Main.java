package utils;

import java.util.List;

import enums.TypeFilter;
import players.Player;
import service.GameService;
import service.PartyGameService;
import service.SetupService;

public class Main {
	
	private static SetupService setupService = new SetupServiceImpl();
	private static GameService gameService = new GameServiceImpl();
	private static PartyGameService partyGameService = new PartyGameServiceImpl();

    public static void main(String[] args) {
        int response = 1;
        while (response == 1) {
            List<Player> players = setupService.setupGame();
            partyGameService.playGame(players);
            response = gameService.addQuestion("Voulez-vous refaire une partie ? 1:OUI, 2:NON", TypeFilter.CHOOSEATACK);
        }
        System.out.println("Merci d'avoir joué !! :)");
    }

}
