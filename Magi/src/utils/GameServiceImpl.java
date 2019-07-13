package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import enums.TypeFilter;
import service.GameService;
import service.PlayerConditionsService;

/**
 * Permet de créer des inputs pendant la partie
 *
 * @author Antoine Janvrot
 * @version 22 mai 2019
 */
public class GameServiceImpl implements GameService {

	private PlayerConditionsService playerConditionService;

	/**
	 * Récupère l'entrée dans le terminal
	 *
	 * @return La valeur de l'entrée
	 */
	@SuppressWarnings("resource")
	@Override
	public int getResponse() {
		try {
			Scanner sc = new Scanner(System.in);
			int response = sc.nextInt();
			return (Integer) response;
		} catch (InputMismatchException e) {
			return -1;
		}
	}

	@Override
	public int addQuestion(String question, TypeFilter filter) {
		System.out.println(question);
		if (!playerConditionService.checkCondition(question, filter)) {
			addQuestion(question, filter);
		} else
			return playerConditionService.checkCondition(question, filter);
	}

	public void setPlayerConditionsService(PlayerConditionsService playerConditionService) {
		this.playerConditionService = playerConditionService;
	}

}
