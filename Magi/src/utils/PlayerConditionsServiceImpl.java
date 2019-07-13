package utils;

import enums.TypeFilter;
import service.GameService;
import service.PlayerConditionsService;

public class PlayerConditionsServiceImpl implements PlayerConditionsService {
	
	private GameService gameService;

	private boolean chooseClassCondition(int result) {
        return (result > 0 && result < 4);
    }

    private boolean choosePlayers(int result) {
        return result > 1;
    }

    private boolean chooseLvl(int result) {
        return (result > 0 && result <= 100);
    }

    private boolean chooseStats(int result) {
        return (result >= 0 && result <= 100);
    }

    private boolean chooseAttack(int result) {
        return (result > 0 && result < 3);
    }

    private boolean chooseTarget(int result) {
        return (result > 0);
    }

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
    
    public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
