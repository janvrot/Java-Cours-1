package players;

import static enums.TypeAction.DAMAGE;
import static enums.TypeStat.LIFE;
import static enums.TypeTarget.OTHER;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import enums.TypeAction;
import enums.TypeKeys;
import enums.TypeTarget;

/**
 * Génère un objet Mage
 *
 * @author Antoine Janvrot
 * @version 22 mai 2019
 */
public class Mage extends Player {

	/**
	 * Instanciation du Mage sans paramètres
	 */
	public Mage() {
	}

	/**
	 * Instanciation du Mage avec paramètres
	 * 
	 * @param lvl
	 *            Le niveau du Mage
	 * @param life
	 *            Les points de vie du Mage
	 * @param strength
	 *            La force du Mage
	 * @param agility
	 *            L'agilité du Mage
	 * @param intelligence
	 *            L'intelligence du Mage
	 * @param name
	 *            le nom du Mage
	 */
	public Mage(int lvl, int life, int strength, int agility, int intelligence, String name) {
		super(lvl, life, strength, agility, intelligence, name);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Bienvenue Mage, voici tes statistiques : " + super.toString();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<TypeKeys, Object>> basicAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.putAll(super.buildAction(super.getIntelligence(), DAMAGE, OTHER, null));
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
		actions.putAll(super.buildAction(super.getIntelligence() * 2, TypeAction.BOOST, TypeTarget.MYSELF, LIFE));
		result.add(actions);
		return result;
	}
}
