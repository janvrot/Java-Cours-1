package utils;

import enums.TypeFilter;
import service.GameService;
import service.PlayerConditionsService;

/**
 * Gestion des filtres appliqués aux réponses aux questions
 *
 * @author Antoine Janvrot
 * @version 16 juil. 2019
 */
public class PlayerConditionsServiceImpl implements PlayerConditionsService {
	
	private static GameService gameService = new GameServiceImpl();

	/**
	 * Filtre lors de la sélection de classe des joueurs
	 *
	 * @param result
	 * 		input non filtré
	 * @return
	 * 		correspondance de l'input avec le filtre
	 */
	private boolean chooseClassCondition(int result) {
        return (result > 0 && result < 4);
    }

	/**
	 * Filtre lors de la sélection des joueurs
	 *
	 * @param result
	 * 		input non filtré
	 * @return
	 * 		correspondance de l'input avec le filtre
	 */
    private boolean choosePlayers(int result) {
        return (result > 1 && result <= 10);
    }

    /**
	 * Filtre lors de la sélection des niveaux
	 *
	 * @param result
	 * 		input non filtré
	 * @return
	 * 		correspondance de l'input avec le filtre
	 */
    private boolean chooseLvl(int result) {
        return (result > 0 && result <= 100);
    }

    /**
	 * Filtre lors de la sélection des statistiques
	 *
	 * @param result
	 * 		input non filtré
	 * @return
	 * 		correspondance de l'input avec le filtre
	 */
    private boolean chooseStats(int result) {
        return (result >= 0 && result <= 100);
    }

    /**
	 * Filtre lors de la sélection des attaques
	 *
	 * @param result
	 * 		input non filtré
	 * @return
	 * 		correspondance de l'input avec le filtre
	 */
    private boolean chooseAttack(int result) {
        return (result > 0 && result < 3);
    }

    /**
	 * Filtre lors de la sélection des cibles des attaques
	 *
	 * @param result
	 * 		input non filtré
	 * @return
	 * 		correspondance de l'input avec le filtre
	 */
    private boolean chooseTarget(int result) {
        return (result > 0);
    }

    /**
     * Applique le filtre présent en paramètre
     *
     * @param response
     * 		input non filtré
     * @param filter
     * 		le filtre à appliquer
     * @return
     * 		la validité de l'input en fonction du filtre
     */
    public boolean addFilter(int response, TypeFilter filter) {
        if (filter != null && response != -1) {
            switch (filter) {
                case CHOOSEPLAYERS:
                    return choosePlayers(response);
                case CHOOSECLASS:
                    return chooseClassCondition(response);
                case CHOOSESTATS:
                    return chooseStats(response);
                case CHOOSEATACK:
                    return chooseAttack(response);
                case CHOOSETARGET:
                    return chooseTarget(response);
                case CHOOSELVL:
                    return chooseLvl(response);
                default:
                    return false;
            }
        } else {
            return response != -1;
        }
    }
    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
	public int checkCondition(String question, TypeFilter filter) {
		int finalResponse = gameService.getResponse();
		while (!addFilter(finalResponse, filter)) {
			System.out.println("Paramètre invalide");
			System.out.println(question);
			finalResponse = gameService.getResponse();
		}
		return finalResponse;
	}
    
    public static void setGameService(GameService gameService) {
		PlayerConditionsServiceImpl.gameService = gameService;
	}
}
