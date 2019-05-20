package players;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import main.enums.TypeAction;
import main.enums.TypeKeys;
import main.enums.TypeTarget;

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
		actions.putAll(super.buildAction(super.getStrength(), TypeAction.DAMAGE, TypeTarget.OTHER));
		result.add(actions);
		return result;
	}

	@Override
	public List<Map<TypeKeys, Object>> specialAttack() {
		List<Map<TypeKeys, Object>> result = new ArrayList<>();
		Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
		actions.putAll(super.buildAction((super.getLife() - super.getStrength() / 2), TypeAction.DAMAGE, TypeTarget.MYSELF));
		actions.putAll(super.buildAction(super.getStrength() * 2, TypeAction.DAMAGE, TypeTarget.OTHER));
		result.add(actions);
		return result;
	}


}
