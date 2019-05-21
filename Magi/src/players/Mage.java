package players;

import static main.enums.TypeAction.DAMAGE;
import static main.enums.TypeStat.LIFE;
import static main.enums.TypeTarget.OTHER;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import main.enums.TypeAction;
import main.enums.TypeKeys;
import main.enums.TypeStat;
import main.enums.TypeTarget;

public class Mage extends Player {

    public Mage() {
    }

    public Mage(int lvl, int life, int strength, int agility, int intelligence, String name) {
        super(lvl, life, strength, agility, intelligence, name);
    }

    @Override
    public String toString() {
        return "Mage{} " + super.toString();
    }

	@Override
	public List<Map<TypeKeys, Object>> basicAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.putAll(super.buildAction(super.getAgility(), DAMAGE, OTHER, null));
		result.add(actions);
		return result;
	}

	@Override
	public List<Map<TypeKeys, Object>> specialAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.putAll(super.buildAction(super.getIntelligence() * 2, TypeAction.BOOST, TypeTarget.MYSELF, LIFE));
		result.add(actions);
		return result;
	}
}
