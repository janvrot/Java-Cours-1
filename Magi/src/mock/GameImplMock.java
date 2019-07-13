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
package mock;

import enums.TypeFilter;
import service.GameService;

public class GameImplMock implements GameService {

	@Override
	public int getResponse() {
		return 0;
	}

	@Override
	public int addQuestion(String question, TypeFilter filter) {
		return 0;
	}

}
