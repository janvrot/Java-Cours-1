package players;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import main.enums.TypeAction;
import main.enums.TypeKeys;
import main.enums.TypeStat;
import main.enums.TypeTarget;

import static main.enums.TypeKeys.*;

/**
 * Class représentant les statistiques de base du joueur
 */
public abstract class Player {

    private int lvl;
    private int life;
    private int strength;
    private int agility;
    private int intelligence;
    private String name;

    public Player() {

    }

    public Player(int lvl, int life, int strength, int agility, int intelligence, String name) {
        this.lvl = lvl;
        this.life = life;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public abstract List<Map<TypeKeys, Object>> basicAttack();

    public abstract List<Map<TypeKeys, Object>> specialAttack();

    public Map<TypeKeys, Object> buildAction(int amount, TypeAction action, TypeTarget target, TypeStat stat) {
        Map<TypeKeys, Object> actions = new EnumMap<>(TypeKeys.class);
        actions.put(AMOUNT, amount);
        actions.put(ACTION, action);
        actions.put(TARGET, target);
        actions.put(STAT, stat);
        return actions;
    }

    @Override
    public String toString() {
        return "Player{" +
                "lvl=" + lvl +
                ", life=" + life +
                ", strength=" + strength +
                ", agility=" + agility +
                ", intelligence=" + intelligence +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getLvl() == player.getLvl() &&
                getLife() == player.getLife() &&
                getStrength() == player.getStrength() &&
                getAgility() == player.getAgility() &&
                getIntelligence() == player.getIntelligence();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLvl(), getLife(), getStrength(), getAgility(), getIntelligence());
    }
}
