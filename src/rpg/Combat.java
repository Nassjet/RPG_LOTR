package rpg;

import rpg.Monstres.Monstre;
import java.util.Scanner;

public class Combat {
    private Personnage joueur;
    private Monstre monstre;
    private Carte carte;
    private char direction;
    private boolean escape;

    public Combat(Personnage joueur, Monstre monstre, Carte carte, char direction) {
        this.joueur = joueur;
        this.monstre = monstre;
        this.carte = carte;
        this.direction = direction;
    }

    public String demarrerCombat() {
        System.out.println("Un " + monstre.getNom() + " apparait !");
        // Boucle de combat jusqu'à ce qu'il y ait un vainqueur ou que le joueur fuie
        while (joueur.getHealth() > 0 && monstre.getHealth() > 0 && !escape) {
            tourJoueur();
            // Vérifie si le monstre est toujours en vie après l'attaque du joueur
            if (monstre.getHealth() > 0 && !escape) {
                tourMonstre();
            }
        }
        // Retourne le résultat du combat
        if (escape) {
            System.out.println("Vous avez fui le combat !");
            carte.fuite(joueur, direction); // Appeler la méthode de fuite sur la carte
            return "flee";
        } else if (joueur.getHealth() > 0) {
            System.out.println("Vous avez vaincu " + monstre.getNom() + " !");
            return "win";
        } else {
            System.out.println("Vous avez été vaincu par " + monstre.getNom() + "...");
            return "lose";
        }
    }

    private void tourJoueur() {
        System.out.println("===============");
        System.out.println("C'est votre tour !");
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Attaquer");
        System.out.println("2. Consulter l'inventaire");
        System.out.println("3. Regarder vos stats");
        System.out.println("4. Regarder les stats de l'adversaire");
        System.out.println("5. Fuir");

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
            case 5:
                escape = true;
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
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

    private void tourMonstre() {
        System.out.println("===============");
        System.out.println("C'est au tour du " + monstre.getNom() + " de vous attaquer !");
        int degatsInfliges = monstre.getAttack() - joueur.getDefense();
        if (degatsInfliges < 0) degatsInfliges = 0;
        joueur.setHealth(joueur.getHealth() - degatsInfliges);
        System.out.println("Le " + monstre.getNom() + " vous attaque et inflige " + degatsInfliges + " points de dégâts !");
    }

    private void attaque() {
        int degatsInfliges = joueur.getAttack() - monstre.getDefense();
        if (degatsInfliges < 0) degatsInfliges = 0; // éviter des dégâts négatifs
        monstre.setHealth(monstre.getHealth() - degatsInfliges);
        System.out.println("Vous attaquez le " + monstre.getNom() + " et lui infligez " + degatsInfliges + " points de dégâts !");
    }
}
