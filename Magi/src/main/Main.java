package main;

public class Main {

    public static void main(String[] args) {

        Player guerrier = new Guerrier(5,5,5,5,5,"Joueur 1", TypePlayer.Guerrier);

        System.out.println(guerrier);

        guerrier.specialAttack();

        System.out.println(guerrier);
    }
}
