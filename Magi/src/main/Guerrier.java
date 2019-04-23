package main;

import java.util.Objects;

public class Guerrier extends Player {

    private TypePlayer typePlayer;

    public Guerrier() {
    }

    public Guerrier(int lvl, int life, int strength, int agility, int intelligence, String name, TypePlayer typePlayer) {
        super(lvl, life, strength, agility, intelligence, name);
        this.typePlayer = typePlayer;
    }

    public TypePlayer getTypePlayer() {
        return typePlayer;
    }

    public void setTypePlayer(TypePlayer typePlayer) {
        this.typePlayer = typePlayer;
    }

    @Override
    public String toString() {
        return "Guerrier{" +
                "typePlayer=" + typePlayer +
                "} " + super.toString();
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
