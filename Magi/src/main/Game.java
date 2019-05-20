package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {

	public Game() {
	}

	public void setupGame() {
		int nbPlayers = addQuestion("Choisissez le nombre de joueurs", null);
		List<Player> players = new ArrayList<>(nbPlayers);
		for(int i = 0;i<nbPlayers;i++) {
			Player player = chooseClass();
			player.setName("Joueur " + i+1);
			players.add(player);
			System.out.println(player);
		}
		System.out.println(players);
	}

	public int getResponse() {
		Scanner sc = new Scanner(System.in);
		try {
			int response = sc.nextInt();
			return (Integer) response;
		} catch (InputMismatchException e) {
			return -1;
		}

	}
	
	public int addQuestion(String question, String filterName) {
		System.out.println(question);
		int finalResponse = getResponse();
		while (!PlayerConditions.addFilter(finalResponse, filterName)) {
			System.out.println("Paramètre invalide");
			System.out.println(question);
			finalResponse = getResponse();
		}
		return finalResponse;
	}
	
	protected Player chooseClass() {
		int playerType = addQuestion("Choisissez une classe :", "chooseClass");
		switch (playerType) {
		default:
			return new Guerrier();
		}
	}
}
