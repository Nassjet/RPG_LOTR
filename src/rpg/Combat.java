package rpg;

import rpg.Monstres.Monstre;

import java.util.Scanner;

public class Combat {

    private Personnage joueur;
    private Monstre monstre;

    public Combat(Personnage joueur, Monstre monstre) {
        this.joueur = joueur;
        this.monstre = monstre;
    }

    public void demarrerCombat(){
        System.out.println("Un" + monstre.getNom() + " apparait !");
    }

    private void tourJoueur(){
        System.out.println("===============");
        System.out.println("C'est votre tour !");

        System.out.println("Que voulez vous faire ?");
        Scanner choixCombat = new Scanner(System.in);
        switch (choixCombat.nextInt()) {
            case 1:
                attaque();
                break;
            case 2:
                joueur.afficherInventaire();
                break;
            case 3:
                joueur.getStats();
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer");
                try {
                    // Met en pause le programme pendant 1 seconde (1000 millisecondes)
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // Gérer une éventuelle interruption
                    e.printStackTrace();
                }
                tourJoueur();
        }
    }

    private void attaque(){

    }
}
