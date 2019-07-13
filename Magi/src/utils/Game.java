package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import enums.TypeFilter;

/**
 * Permet de créer des inputs pendant la partie
 *
 * @author Antoine Janvrot
 * @version 22 mai 2019
 */
public class Game {

	/**
	 * Bloquage de l'instanciation
	 */
	private Game() {
	}

	/**
	 * Récupère l'entrée dans le terminal
	 *
	 * @return La valeur de l'entrée
	 */
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

	/**
	 * Pose une question et filtre la réponse
	 *
	 * @param question
	 *            La question posée
	 * @param filter
	 *            Le filtre appliqué à la réponse
	 * @return La valeur entrée dans le terminal
	 */
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
