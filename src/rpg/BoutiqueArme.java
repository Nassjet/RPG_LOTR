package rpg;

import rpg.Armes.Arme;
import rpg.Personnage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoutiqueArme {
    protected List<Arme> desArmes = new ArrayList<>();
    protected List<Arme> armesDisponibles = new ArrayList<>();

    public BoutiqueArme() {
        // Ajouter toutes les armes prédéfinies à la liste
        desArmes.add(new Arme("Épée usée", 10, 5, "Commun", 15));
        desArmes.add(new Arme("Bâton magique", 8, 2, "Rare", 25));
        desArmes.add(new Arme("Arc elfique", 20, 3, "Légendaire", 50));
        desArmes.add(new Arme("Dague de l'ombre", 15, 1, "Rare", 30));
        desArmes.add(new Arme("Hache de guerre", 25, 7, "Peu commun", 40));
        desArmes.add(new Arme("Marteau du destin", 30, 10, "Légendaire", 60));
    }

    public void genererArmesAleatoires() {
        // Mélanger la liste des armes prédéfinies
        Collections.shuffle(desArmes);

        // Sélectionner les 3 premières armes mélangées
        armesDisponibles = desArmes.subList(0, 3);
    }

    public void afficherArmesBoutique() {
        if (armesDisponibles.isEmpty()) {
            System.out.println("La boutique ne contient aucune arme pour l'instant.");
        } else {
            System.out.println("Voici les armes disponibles dans la boutique : ");
            for (int i = 0; i < armesDisponibles.size(); i++) {
                Arme arme = armesDisponibles.get(i);
                System.out.println((i + 1) + ". " + arme.getName() + " - Dégâts : " + arme.getDegats() + " - Prix : " + arme.getPrix() + " pièces d'or.");
            }
        }
    }
    public void acheterArme(Personnage personnage, int achat) {
        if (achat < 1 || achat > armesDisponibles.size()) {
            System.out.println("Tu as tapé le numéro d'une arme qui n'existe pas ! ");
            return;
        }

        Arme armeAchat = armesDisponibles.get(achat - 1);
        int prixArme = armeAchat.getPrix();

        if (personnage.getCash() < prixArme) {
            System.out.println("Vous n'avez pas assez de pièces d'or !! Revenez une autre fois.");
        } else {
            personnage.depenserCash(prixArme);
            personnage.getInventaire().ajouterArme(armeAchat);
            armesDisponibles.remove(armeAchat);
            System.out.println("Votre arme a été achetée et ajoutée à votre inventaire.");
        }
    }
}