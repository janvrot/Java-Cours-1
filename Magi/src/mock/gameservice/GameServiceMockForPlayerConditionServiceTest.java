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
 * Créé le 28 juin 2019.
 * </p>
 */
package mock.gameservice;

import enums.TypeFilter;
import service.GameService;

/**
 * Mock pour le test de la classe PlayerConditionService
 *
 * @author Antoine Janvrot
 * @version 16 juil. 2019
 */
public class GameServiceMockForPlayerConditionServiceTest implements GameService {
	int nbAsk = 0;

	@Override
	public int getResponse() {

		if (nbAsk < 4) {
			nbAsk++;
			return 0;
		}
		return 1;
	}

	@Override
	public int addQuestion(String question, TypeFilter filter) {
		return 2;
	}

}
