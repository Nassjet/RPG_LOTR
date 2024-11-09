package rpg;

import rpg.Armes.Arme;

import rpg.Personnage;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inventaire implements ObjetInventaire {
    private ArrayList<Arme> armes; // Liste d'armes

    public Inventaire() {
        this.armes = new ArrayList<>();
    }

    public void ajouterArme(Arme arme) {
        armes.add(arme);
        System.out.println("Arme ajouté à l'inventaire");
    }

    @Override
    public void supprimerArme(Arme arme) {
        // TODO
    }

    public ArrayList<Arme> getArmes() {
        return armes;
    }

    public void afficherArmes(Personnage personnage) {
        if (armes.isEmpty()) {
            System.out.println("Vous n'avez pas d'armes dans votre inventaire.");
        } else {
            System.out.println("Voici les armes de votre inventaire d'Armes : ");
            for (int i = 0; i < armes.size(); i++) {
                Arme arme = armes.get(i);
                System.out.println((i + 1) + ". " + arme.getName() + " - Dégâts : " + arme.getDegats());
            }

            int choix = -1;
            while ( choix < 0 || choix > armes.size() ) {
                // Demander au joueur quelle arme il souhaite utiliser
                Scanner scanner = new Scanner(System.in);
                System.out.println("Quelle arme souhaitez-vous utiliser ? (Entrez le numéro de l'arme ou 0 pour annuler)");
                try {
                    choix = scanner.nextInt();
                    if (choix < 1 || choix > armes.size()) {
                        System.out.println("Option invalide ! Veuillez entrer un nombre.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Option invalide ! Veuillez entrer un nombre.");
                }
            }


            if (choix > 0 && choix <= armes.size()) { // à ne pas toucher parce que sinon boucle
                Arme armeChoisie = armes.get(choix - 1);
                System.out.println("Vous avez choisi d'utiliser : " + armeChoisie.getName());
                personnage.setArmeEquipee(armeChoisie); // équipe l'arme choisie
            } else if (choix == 0) {
                System.out.println("Aucune arme sélectionnée.");
            } else {
                System.out.println("Choix invalide.");
            }
        }
    }
}
