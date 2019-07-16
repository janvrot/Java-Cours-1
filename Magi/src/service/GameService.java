package service;

import enums.TypeFilter;

/**
 * interface de gestion des réponses aux questions
 *
 * @author Antoine Janvrot
 * @version 16 juil. 2019
 */
public interface GameService {
	
	/**
	 * Récupère un input sous forme d'entier
	 *
	 * @return
	 * 	L'entier entré dans le terminal
	 */
	int getResponse();
	
	/**
	 * Pose une question dont la réponse est filtrée
	 *
	 * @param question
	 * 		La question affichée
	 * @param filter
	 * 		Le filtre appliquyé à la réponse
	 * @return
	 * 		La réponse de l'utilisateur
	 */
	int addQuestion(String question, TypeFilter filter);
}

