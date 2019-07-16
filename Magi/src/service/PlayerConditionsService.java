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
package service;

import enums.TypeFilter;

/**
 * Interface de gestion des filtres à appliquer aux questions
 *
 * @author Antoine Janvrot
 * @version 16 juil. 2019
 */
public interface PlayerConditionsService {

	/**
	 * Pose la question et filtre la réponse
	 *
	 * @param question
	 * 		La question posée
	 * @param filter
	 * 		le filtre appliqué à la réponse
	 * @return
	 * 		La réponse si correcte
	 */
	int checkCondition(String question, TypeFilter filter);
}

