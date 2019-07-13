/**
 * <p>
 * Copyright © 2019 THALES Communications, France. All rights reserved.
 * </p>
 * <p>
 * Ce document est la propriété de THALES Communications, France,
 * il ne peut être ni reproduit, ni utilisé, ni communiqué, ni distribué
 * à  des tiers sans son autorisation préalable.
 * </p>
 * <p>
 * Créé le 4 juil. 2019.
 * </p>
 */
package mock.gameservice;

import enums.TypeFilter;
import service.GameService;

public class GameServiceMockForSetupServiceTest implements GameService {
	private int chooseTreat = 0;
	private int nbCall = 0;

	@Override
	public int getResponse() {
		return 1;
	}

	@Override
	public int addQuestion(String question, TypeFilter filter) {
		int result = 0;
		switch (chooseTreat) {
		case 0:
			result = nbCall;
			nbCall++;			
		}
		return result;
	}

	public void setNbCall(int nbCall) {
		this.nbCall = nbCall;
	}
	
	public void setChooseTreat(int chooseTreat) {
		this.chooseTreat = chooseTreat;
	}
}
