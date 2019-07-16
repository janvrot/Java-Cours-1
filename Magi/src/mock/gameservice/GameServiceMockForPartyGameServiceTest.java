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

/**
 * Mock pour le test de la classe PartyGameService
 *
 * @author Antoine Janvrot
 * @version 16 juil. 2019
 */
public class GameServiceMockForPartyGameServiceTest implements GameService {
	int nbAsk = 4;

	@Override
	public int getResponse() {
		return 1;
	}

	@Override
	public int addQuestion(String question, TypeFilter filter) {
		if (nbAsk > 1) {
			nbAsk--;	
		}
		return nbAsk;
	}
	
	public void setNbAsk(int nbAsk) {
		this.nbAsk = nbAsk;
	}
}
