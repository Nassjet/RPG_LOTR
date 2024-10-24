package rpg;

import rpg.Armes.*;
import rpg.Races.Elfe;
import rpg.Races.Hobbit;
import rpg.Races.Homme;
import rpg.Races.Nain;

import java.util.Scanner;

public class Bataille {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le Seigneur des Anneaux RPG !");
        System.out.println("Choisissez votre race : ");
        System.out.println("1. Hobbit");
        System.out.println("2. Homme");
        System.out.println("3. Nain");
        System.out.println("4. Elfe");

        int choix = scanner.nextInt();
        scanner.nextLine();

        // Demander au joueur de choisir un nom
        System.out.println("Veuillez entrer le nom de votre personnage : ");
        String nomPersonnage = scanner.nextLine();

        Personnage personnage;
        BoutiqueArme boutiqueArme = new BoutiqueArme();
        boutiqueArme.genererArmesAleatoires();

        // Création du personnage en fonction du choix de race
        switch (choix) {
            case 1:
                Arme anneau = new AnneauDePouvoir();
                personnage = new Hobbit(nomPersonnage, anneau);
                personnage.setArmeEquipee(anneau);
                personnage.getInventaire().ajouterArme(anneau);
                System.out.println("L'anneau de pouvoir à était équipée et est dans votre inventaire");
                break;
            case 2:
                Arme anduril = new Anduril();
                personnage = new Homme(nomPersonnage, anduril);
                personnage.setArmeEquipee(anduril);
                personnage.getInventaire().ajouterArme(anduril);
                System.out.println("Anduril est dans votre inventaire et est équipée");
                break;
            case 3:
                Arme hache = new HacheDeGuerre();
                personnage = new Nain(nomPersonnage, hache);
                personnage.setArmeEquipee(hache);
                personnage.getInventaire().ajouterArme(hache);
                System.out.println("L'Hache de Guerre de Gimli a était équipée et est dans votre inventaire");
                break;
            case 4:
                Arme arc = new ArcElfique();
                personnage = new Elfe(nomPersonnage, arc);
                personnage.setArmeEquipee(arc);
                personnage.getInventaire().ajouterArme(arc);
                System.out.println("L'arc de Legolas à était équipée et est dans votre inventaire");
                break;
            default:
                System.out.println("Choix invalide, un Hobbit a été sélectionné par défaut.");
                Arme anneauDefault = new AnneauDePouvoir();
                personnage = new Hobbit(nomPersonnage, anneauDefault);
                personnage.setArmeEquipee(anneauDefault);
                personnage.getInventaire().ajouterArme(anneauDefault);
                System.out.println("L'anneau de pouvoir à était équipée et est dans votre inventaire");
                break;
        }

        // Afficher les stats du personnage choisi
        personnage.getStats();

        boolean jeuEnCours = true;
        while (jeuEnCours) {
            System.out.println("\n\n Menu Principal");
            System.out.println("1. Aller dans votre inventaire");
            System.out.println("2. Aller à la boutique");
            System.out.println("3. Afficher vos stats");
            System.out.println("4. Quitter le jeu");

            System.out.print("Choisissez une option : ");
            int choixMenu = scanner.nextInt();

            switch (choixMenu){
                case 1:
                    personnage.afficherInventaire();
                    break;
                case 2:
                    boutiqueArme.afficherArmesBoutique();
                    int achat = scanner.nextInt();
                    boutiqueArme.acheterArme(personnage, achat);
                    break;
                case 3:
                    personnage.getStats();
                    break;
                case 4:
                    System.out.println("Merci d'avoir joué");
                    jeuEnCours  = false;
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        }
    }
}
