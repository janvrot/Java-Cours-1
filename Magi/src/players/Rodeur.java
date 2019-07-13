package players;

import static enums.TypeAction.BOOST;
import static enums.TypeAction.DAMAGE;
import static enums.TypeStat.AGILITY;
import static enums.TypeTarget.MYSELF;
import static enums.TypeTarget.OTHER;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import enums.TypeKeys;

/**
 * Génère un objet Rodeur
 *
 * @author Antoine Janvrot
 * @version 22 mai 2019
 */
public class Rodeur extends Player {

	/**
	 * Instanciation du Rodeur sans paramètres
	 */
	public Rodeur() {
	}

	/**
	 * Instanciation du Rodeur avec paramètres
	 * 
	 * @param lvl
	 *            Le niveau du Rodeur
	 * @param life
	 *            Les points de vie du Rodeur
	 * @param strength
	 *            La force du Rodeur
	 * @param agility
	 *            L'agilité du Rodeur
	 * @param intelligence
	 *            L'intelligence du Rodeur
	 * @param name
	 *            le nom du Rodeur
	 */
	public Rodeur(int lvl, int life, int strength, int agility, int intelligence, String name) {
		super(lvl, life, strength, agility, intelligence, name);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Rodeur{} " + super.toString();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<TypeKeys, Object>> basicAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.putAll(super.buildAction(super.getAgility(), DAMAGE, OTHER, null));
		result.add(actions);
		return result;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<TypeKeys, Object>> specialAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.putAll(super.buildAction(super.getLvl() / 2, BOOST, MYSELF, AGILITY));
		result.add(actions);
		return result;
	}

}
