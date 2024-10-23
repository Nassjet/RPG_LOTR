package rpg;

import rpg.Armes.Arme;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventaire {
    private ArrayList<Arme> armes; // Liste d'armes

    public Inventaire() {
        this.armes = new ArrayList<>();
    }

    public void ajouterArme(Arme arme) {
        armes.add(arme);
        System.out.println("Arme ajouté à l'inventaire");
    }

    public ArrayList<Arme> getArmes() {
        return armes;
    }

    public void afficherArmes() {
        if (armes.isEmpty()) {
            System.out.println("Vous n'avez pas d'armes dans votre inventaire.");
        } else {
            System.out.println("Voici les armes de votre inventaire d'Armes : ");
            for (int i = 0; i < armes.size(); i++) {
                Arme arme = armes.get(i);
                System.out.println((i + 1) + ". " + arme.getName() + " - Dégâts : " + arme.getDegats());
            }

            // Demander au joueur quelle arme il souhaite utiliser
            Scanner scanner = new Scanner(System.in);
            System.out.println("Quelle arme souhaitez-vous utiliser ? (Entrez le numéro de l'arme ou 0 pour annuler)");

            int choix = scanner.nextInt();

            if (choix > 0 && choix <= armes.size()) {
                Arme armeChoisie = armes.get(choix - 1);
                System.out.println("Vous avez choisi d'utiliser : " + armeChoisie.getName());
                // Ici, vous pouvez ajouter la logique pour équiper l'arme ou l'utiliser
            } else if (choix == 0) {
                System.out.println("Aucune arme sélectionnée.");
            } else {
                System.out.println("Choix invalide.");
            }
        }
    }
}
