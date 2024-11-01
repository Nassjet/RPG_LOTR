package rpg;

import rpg.Armes.*;
import rpg.Monstres.Monstre;
import rpg.Monstres.Orc;
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
                System.out.println("L'anneau de pouvoir a été équipé et est dans votre inventaire");
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
                System.out.println("La hache de guerre de Gimli a été équipée et est dans votre inventaire");
                break;
            case 4:
                Arme arc = new ArcElfique();
                personnage = new Elfe(nomPersonnage, arc);
                personnage.setArmeEquipee(arc);
                personnage.getInventaire().ajouterArme(arc);
                System.out.println("L'arc de Legolas a été équipé et est dans votre inventaire");
                break;
            default:
                System.out.println("Choix invalide, un Hobbit a été sélectionné par défaut.");
                Arme anneauDefault = new AnneauDePouvoir();
                personnage = new Hobbit(nomPersonnage, anneauDefault);
                personnage.setArmeEquipee(anneauDefault);
                personnage.getInventaire().ajouterArme(anneauDefault);
                System.out.println("L'anneau de pouvoir a été équipé et est dans votre inventaire");
                break;
        }

        // Initialiser la carte
        int largeurCarte = 5; // Exemple de largeur
        int hauteurCarte = 5; // Exemple de hauteur
        Carte carte = new Carte(largeurCarte, hauteurCarte);

        // Afficher les stats du personnage choisi
        personnage.getStats();

        boolean jeuEnCours = true;
        while (jeuEnCours) {
            System.out.println("\n\nMenu Principal");
            System.out.println("1. Aller dans votre inventaire");
            System.out.println("2. Afficher vos stats");
            System.out.println("3. Accéder au donjon");
            System.out.println("4. Quitter le jeu");

            System.out.print("Choisissez une option : ");
            int choixMenu = scanner.nextInt();

            switch (choixMenu){
                case 1:
                    personnage.afficherInventaire();
                    break;
                case 2:
                    personnage.getStats();
                    break;
                case 3:
                    System.out.println("Vous entrez dans le donjon...");
                    // Afficher la carte
                    carte.placerJoueurDebut(personnage);
                    carte.ajouterElementsAleatoires(0,0);
                    carte.afficherCarte();
                    boolean dansDonjon = true;
                    while (dansDonjon) {
                        System.out.println("\nActions dans le donjon:");
                        System.out.println("1. Se déplacer");
                        System.out.println("2. Afficher l'inventaire");
                        System.out.println("3. Afficher les stats");
                        System.out.println("4. Quitter le donjon");

                        int choixDonjon = scanner.nextInt();

                        switch (choixDonjon) {
                            case 1:
                                System.out.println("Entrez la direction (N, S, E, O):");
                                char direction = scanner.next().charAt(0);
                                boolean sortie = carte.deplacerJoueur(direction, personnage);
                                carte.afficherCarte();
                                if (sortie) {
                                    dansDonjon = false;
                                    jeuEnCours = false;
                                }
                                break;
                            case 2:
                                personnage.afficherInventaire();
                                break;
                            case 3:
                                personnage.getStats();
                                break;
                            case 4:
                                System.out.println("Vous quittez le donjon.");
                                dansDonjon = false;
                                break;
                            default:
                                System.out.println("Choix invalide, veuillez réessayer.");
                        }
                    }

                    break;
                case 4:
                    System.out.println("Merci d'avoir joué !");
                    jeuEnCours  = false;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        }
    }
}
