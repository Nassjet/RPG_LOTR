package rpg;

import rpg.Armes.*;
import rpg.Monstres.Monstre;
import rpg.Monstres.Orc;
import rpg.Races.Elfe;
import rpg.Races.Hobbit;
import rpg.Races.Homme;
import rpg.Races.Nain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bataille {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le Seigneur des Anneaux RPG !");

        int choix = 0;
        while (true) {
            System.out.println("Choisissez votre race : ");
            System.out.println("1. Hobbit");
            System.out.println("2. Homme");
            System.out.println("3. Nain");
            System.out.println("4. Elfe");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (choix >= 1 && choix <= 4) {
                    scanner.nextLine(); // Consommer la fin de ligne
                    break; // Sortir de la boucle si la saisie est valide
                }
            } else {
                scanner.nextLine(); // Consommer l'entrée invalide
            }
            System.out.println("Entrée invalide, veuillez saisir un nombre entre 1 et 4.");
        }

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
        int largeurCarte = 8; // Exemple de largeur
        int hauteurCarte = 8; // Exemple de hauteur

        Carte carte = new Carte(largeurCarte, hauteurCarte);

        // Afficher les stats du personnage choisi
        personnage.getStats();

        boolean jeuEnCours = true;
        while (jeuEnCours) {

            int choixMenu = -1; // Valeur par défaut en dehors de la plage d'options pour entrer dans la boucle

            while (choixMenu < 1 || choixMenu > 4) { // Modifier la plage pour ne plus inclure 5
                System.out.println("\n\nMenu Principal");
                System.out.println("1. Aller dans votre inventaire");
                System.out.println("2. Afficher vos stats");
                System.out.println("3. Accéder au donjon");
                System.out.println("4. Quitter le jeu");
                System.out.print("Choisissez une option : ");

                try {
                    choixMenu = scanner.nextInt();
                    if (choixMenu < 1 || choixMenu > 4) { // Mettre à jour la plage
                        System.out.println("Option invalide ! Veuillez entrer un nombre entre 1 et 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide ! Veuillez entrer un nombre.");
                    scanner.nextLine(); // Vider le scanner pour éviter une boucle infinie
                }
            }


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
                    carte.ajouterElementsAleatoires(3,3);
                    carte.afficherCarte();
                    boolean dansDonjon = true;
                    while (dansDonjon) {
                        int choixDonjon = -1;
                        while (choixDonjon < 1 || choixDonjon > 5) {  // Ajout de l'option pour sauvegarder dans le donjon
                            System.out.println("\nActions dans le donjon:");
                            System.out.println("1. Se déplacer");
                            System.out.println("2. Afficher l'inventaire");
                            System.out.println("3. Afficher les stats");
                            System.out.println("4. Quitter le donjon");
                            System.out.println("5. Sauvegarder la carte");

                            try {
                                choixDonjon = scanner.nextInt();
                                if (choixDonjon < 1 || choixDonjon > 5) {
                                    System.out.println("Option invalide ! Veuillez entrer un nombre.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Option invalide ! Veuillez entrer un nombre.");
                                scanner.nextLine();
                            }
                        }
                        switch (choixDonjon) {
                            case 1:
                                boolean enDeplacement = true;
                                carte.afficherCarte();
                                while (enDeplacement) {
                                    System.out.println("Entrez la direction (H pour haut, B pour bas, D pour droite, G pour gauche), ou 'Q' pour revenir au menu:");
                                    char direction = scanner.next().toUpperCase().charAt(0);

                                    if (direction == 'Q') {  // Permet de revenir au menu de base
                                        enDeplacement = false;
                                        System.out.println("Retour au menu principal du donjon.");
                                    } else if (direction == 'H' || direction == 'B' || direction == 'D' || direction == 'G') {
                                        boolean sortie = carte.deplacerJoueur(direction, personnage);
                                        carte.afficherCarte();

                                        if (sortie) {
                                            System.out.println("Vous avez atteint la sortie !");
                                            dansDonjon = false;
                                            jeuEnCours = false;
                                            enDeplacement = false; // Sort de la boucle de déplacement
                                        }
                                    } else {
                                        System.out.println("Direction invalide. Veuillez entrer une direction correcte (H, B, D, G) ou 'Q' pour quitter.");
                                    }
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
                            case 5:  // Option pour sauvegarder dans le donjon
                                System.out.println("Sauvegarde de la carte...");
                                carte.sauvegarderCarte("carte_sauvegardee.ser");
                                System.out.println("Carte sauvegardée !");
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
