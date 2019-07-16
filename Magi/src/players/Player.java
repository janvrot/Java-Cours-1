package players;

import static enums.TypeKeys.ACTION;
import static enums.TypeKeys.AMOUNT;
import static enums.TypeKeys.STAT;
import static enums.TypeKeys.TARGET;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import enums.TypeAction;
import enums.TypeKeys;
import enums.TypeStat;
import enums.TypeTarget;

/**
 * Class abstraite représentant les statistiques de base du joueur
 *
 * @author Antoine Janvrot
 * @version 3 juin 2019
 */
public abstract class Player {

	private int lvl;
	private int life;
	private int strength;
	private int agility;
	private int intelligence;
	private String name;

	/**
	 * Instanciation du Player sans paramètres
	 */
	public Player() {

	}

	/**
	 * Instanciation du Player avec paramètres
	 * 
	 * @param lvl
	 *            Le niveau du Player
	 * @param life
	 *            Les points de vie du Player
	 * @param strength
	 *            La force du Player
	 * @param agility
	 *            L'agilité du Player
	 * @param intelligence
	 *            L'intelligence du Player
	 * @param name
	 *            le nom du Player
	 */
	public Player(int lvl, int life, int strength, int agility, int intelligence, String name) {
		this.lvl = lvl;
		this.life = life;
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.name = name;
	}

	/**
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            {@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return {@link #lvl}
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            {@link #lvl}
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	/**
	 * @return {@link #life}
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life
	 *            {@link #life}
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return {@link #strength}
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength
	 *            {@link #strength}
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return {@link #agility}
	 */
	public int getAgility() {
		return agility;
	}

	/**
	 * @param agility
	 *            {@link #agility}
	 */
	public void setAgility(int agility) {
		this.agility = agility;
	}

	/**
	 * @return {@link #intelligence}
	 */
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * @param intelligence
	 *            {@link #intelligence}
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	/**
	 * Méthode abstraite pour les attaques de base des joueurs
	 *
	 * @return Les différents paramètres liés à une attaque de base
	 */
	public abstract List<Map<TypeKeys, Object>> basicAttack();

	/**
	 * Méthode abstraite pour les attaques spéciales des joueurs
	 *
	 * @return Les différents paramètres liés à une attaque spéciales
	 */
	public abstract List<Map<TypeKeys, Object>> specialAttack();

	/**
	 * Regroupe les différents paramètres liés à une attaque
	 *
	 * @param amount
	 *            La quantité de points pouvant être retirés/ajoutés
	 * @param action
	 *            Le type d'action effectuée
	 * @param target
	 *            La cible de l'attaque
	 * @param stat
	 *            La statistique ciblée par l'attaque
	 * @return Les différents paramètres liés à une attaque
	 */
	public Map<TypeKeys, Object> buildAction(int amount, TypeAction action, TypeTarget target, TypeStat stat) {
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.put(AMOUNT, amount);
		actions.put(ACTION, action);
		actions.put(TARGET, target);
		actions.put(STAT, stat);
		return actions;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "lvl=" + lvl +
				", life=" + life +
				", strength=" + strength +
				", agility=" + agility +
				", intelligence=" + intelligence +
				", name='" + name + '\'';
	}
}
