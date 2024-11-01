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

    public String demarrerCombat() {
        System.out.println("Un " + monstre.getNom() + " apparait !");

        // Boucle de combat jusqu'à ce qu'il y ait un vainqueur
        while (joueur.getHealth() > 0 && monstre.getHealth() > 0) {
            tourJoueur(); // Le joueur effectue son tour

            // Vérifie si le monstre est toujours en vie après l'attaque du joueur
            if (monstre.getHealth() > 0) {
                tourMonstre(); // Si le monstre est en vie, il effectue son tour
            }
        }

        // Retourne le résultat du combat
        if (joueur.getHealth() > 0) {
            System.out.println("Vous avez vaincu " + monstre.getNom() + " !");
            return "win";
        } else {
            System.out.println("Vous avez été vaincu par " + monstre.getNom() + "...");
            return "lose";
        }
    }



    public void tourJoueur(){
        System.out.println("===============");
        System.out.println("C'est votre tour !");

        System.out.println("Que voulez vous faire ?");
        System.out.println("1. Attaquer");
        System.out.println("2. Consulter l'inventaire");
        System.out.println("3. Regarder vos stats");
        System.out.println("4. Regarder les stats de l'adversaire");
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
            case 4:
                monstre.getStats();
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

    public void tourMonstre() {
        System.out.println("===============");
        System.out.println("C'est au tour du " + monstre.getNom() + " de vous attaquer !");

        int degatsInfliges = monstre.getAttack() - joueur.getDefense();
        if (degatsInfliges < 0) degatsInfliges = 0;

        joueur.setHealth(joueur.getHealth() - degatsInfliges);
        System.out.println("Le " + monstre.getNom() + " vous attaque et inflige " + degatsInfliges + " points de dégâts !");

        if (joueur.getHealth() <= 0) {
            System.out.println("Vous avez été vaincu par le " + monstre.getNom() + "...");
            return;
        }
        tourJoueur(); // Revenir au tour du joueur
    }


    private void attaque() {
        int degatsInfliges = joueur.getAttack() - monstre.getDefense();
        if (degatsInfliges < 0) degatsInfliges = 0; // éviter des dégâts négatifs

        monstre.setHealth(monstre.getHealth() - degatsInfliges);
        System.out.println("Vous attaquez le " + monstre.getNom() + " et lui infligez " + degatsInfliges + " points de dégâts !");

        if (monstre.getHealth() <= 0) {
            System.out.println("Vous avez vaincu le " + monstre.getNom() + " !");
            return;
        }
        tourMonstre(); // Passer au tour du monstre
    }

}
