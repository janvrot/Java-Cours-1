package main.enums;

/**
 * Player est l'enumeration donnant le type du joueur
 *
 * @author Antoine JANVROT
 */
public enum TypePlayer {
    Guerrier("Guerrier"),
    Rodeur("Rodeur"),
    Mage("Mage");

    private String type;

    TypePlayer() {

    }

    TypePlayer(String type) {
        this.type = type;
    }}
