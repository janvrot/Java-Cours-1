package players;

import static enums.TypeAction.DAMAGE;
import static enums.TypeTarget.MYSELF;
import static enums.TypeTarget.OTHER;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import enums.TypeKeys;

/**
 * Génère un objet Guerrier
 *
 * @author Antoine Janvrot
 * @version 22 mai 2019
 */
public class Guerrier extends Player {

	/**
	 * Instanciation du Guerrier sans paramètres
	 */
	public Guerrier() {
	}

	/**
	 * Instanciation du Guerrier avec paramètres
	 * 
	 * @param lvl
	 * 		Le niveau du Guerrier
	 * @param life
	 * 		Les points de vie du Guerrier
	 * @param strength
	 * 		La force du Guerrier
	 * @param agility
	 * 		L'agilité du Guerrier
	 * @param intelligence
	 * 		L'intelligence du Guerrier
	 * @param name
	 * 
	 */
	public Guerrier(int lvl, int life, int strength, int agility, int intelligence, String name) {
		super(lvl, life, strength, agility, intelligence, name);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Guerrier{} " + super.toString();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<TypeKeys, Object>> basicAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.putAll(super.buildAction(super.getStrength(), DAMAGE, OTHER, null));
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
		result.add(super.buildAction(super.getStrength() / 2, DAMAGE, MYSELF, null));
		result.add(super.buildAction(super.getStrength() * 2, DAMAGE, OTHER, null));
		return result;
	}
}
