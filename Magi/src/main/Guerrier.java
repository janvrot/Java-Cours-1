package main;

public class Guerrier extends Player {

    public Guerrier() {
    }

    public Guerrier(int lvl, int life, int strength, int agility, int intelligence, String name) {
        super(lvl, life, strength, agility, intelligence, name);
    }

    @Override
    public String toString() {
        return "Guerrier{" + "} " + super.toString();
    }

    @Override
    public int basicAttack() {
        return super.getStrength();
    }

    @Override
    public int specialAttack() {
        super.setLife(super.getLife() - super.getStrength() / 2);
        return super.getStrength() * 2;
    }


}
