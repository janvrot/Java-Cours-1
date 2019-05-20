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
 * Créé le 17 mai 2019.
 * </p>
 */
package main;

public final class PlayerConditions {

	private PlayerConditions() {

	}

	public static boolean chooseClassCondition(int result) {
		return (result < 1 || result > 3) ? false : true;
	}

	public static boolean addFilter(int response, String filterName) {
		if (filterName != null && response != -1) {
			switch (filterName) {
			case "chooseClass":
				return chooseClassCondition(response);
			default:
				return false;
			}
		} else if (response == -1) {
			return false;
		} else {
			return true;
		}
	}
}
