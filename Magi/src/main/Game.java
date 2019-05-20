package main;

import static main.enums.TypeFilter.CHOOSEPLAYERS;
import static utils.Setup.chooseClass;
import static utils.Setup.chooseStat;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import main.enums.TypeFilter;
import players.Player;
import utils.PlayerConditions;

public class Game {

	private Game() {
	}

	public static List<Player> setupGame() {
		int nbPlayers = addQuestion("Choisissez le nombre de joueurs", CHOOSEPLAYERS);
		List<Player> players = new ArrayList<>(nbPlayers);
		for (int i = 0; i < nbPlayers; i++) {
			Player player = chooseClass();
			player.setName("Joueur " + (i + 1));
			player = chooseStat(player);
			players.add(player);
			System.out.println(player);
		}
		return players;
	}
	
	public static void doGame(List<Player> players) {
		
	}

	@SuppressWarnings("resource")
	public static int getResponse() {
		try {
			Scanner sc = new Scanner(System.in);
			int response = sc.nextInt();
			return (Integer) response;
		} catch (InputMismatchException e) {
			return -1;
		}

	}

	public static int addQuestion(String question, TypeFilter filter) {
		System.out.println(question);
		int finalResponse = getResponse();
		while (!PlayerConditions.addFilter(finalResponse, filter)) {
			System.out.println("Paramètre invalide");
			System.out.println(question);
			finalResponse = getResponse();
		}
		return finalResponse;
	}
}
