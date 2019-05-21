package players;

import static main.enums.TypeAction.BOOST;
import static main.enums.TypeAction.DAMAGE;
import static main.enums.TypeStat.AGILITY;
import static main.enums.TypeTarget.MYSELF;
import static main.enums.TypeTarget.OTHER;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import main.enums.TypeKeys;

public class Rodeur extends Player {

    public Rodeur() {
    }

    public Rodeur(int lvl, int life, int strength, int agility, int intelligence, String name) {
        super(lvl, life, strength, agility, intelligence, name);
    }

    @Override
    public String toString() {
        return "Rodeur{} " + super.toString();
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
		actions.putAll(super.buildAction(super.getLvl() / 2, BOOST, MYSELF, AGILITY));
		result.add(actions);
		return result;
	}


}
