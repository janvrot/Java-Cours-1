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
 * Créé le 3 juil. 2019.
 * </p>
 */
package mock.playerconditionsservice;

import enums.TypeFilter;
import service.PlayerConditionsService;

public class PlayerConditionServiceMockForGameServiceTest implements PlayerConditionsService {

	@Override
	public int checkCondition(String question, TypeFilter filter) {
		return 1;
	}

}

