package players;

import static main.enums.TypeAction.DAMAGE;
import static main.enums.TypeTarget.MYSELF;
import static main.enums.TypeTarget.OTHER;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import main.enums.TypeKeys;

public class Guerrier extends Player {

	public Guerrier() {
	}

	public Guerrier(int lvl, int life, int strength, int agility, int intelligence, String name) {
		super(lvl, life, strength, agility, intelligence, name);
	}

	@Override
	public String toString() {
		return "Guerrier{} " + super.toString();
	}

	@Override
	public List<Map<TypeKeys, Object>> basicAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.putAll(super.buildAction(super.getStrength(), DAMAGE, OTHER, null));
		result.add(actions);
		return result;
	}

	@Override
	public List<Map<TypeKeys, Object>> specialAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		result.add(super.buildAction(super.getStrength() / 2, DAMAGE, MYSELF, null));
		result.add(super.buildAction(super.getStrength() * 2, DAMAGE, OTHER, null));
		return result;
	}
}
