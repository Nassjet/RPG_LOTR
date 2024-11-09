package rpg;

import rpg.*;
import rpg.Armes.AnneauDePouvoir;
import rpg.Monstres.Orc;
import rpg.Monstres.Troll;
import rpg.Monstres.MageNoir;
import rpg.Monstres.Monstre;
import rpg.Races.Hobbit;

import java.io.*;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Carte implements Serializable {
    private int largeur; // Largeur de la carte
    private int hauteur; // Hauteur de la carte
    private Tile[][] map; // Grille de Tiles
    private int joueurX; // Position X du joueur
    private int joueurY; // Position Y du joueur
    private Random random = new Random(); // Générateur de nombres aléatoires

    // Constructeur pour initialiser la carte avec une largeur et une hauteur
    public Carte(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.map = new Tile[largeur][hauteur];
        // Initialiser la carte avec des tuiles vides par défaut
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                map[y][x] = new EmptyTile();
            }
        }
        // appel de la méthode pour placer la boutique lors de la génération de la carte
        placerBoutiqueAuCentre();
        placerEndTile();
        // Initialisation de la position du joueur en haut à gauche
        this.joueurX = 0;
        this.joueurY = 0;
    }

    // Getters pour accéder à ces attributs si nécessaire
    public int getLargeur() { return largeur; }
    public int getHauteur() { return hauteur; }
    public int getJoueurX() { return joueurX; }
    public int getJoueurY() { return joueurY; }

    // Afficher la carte en incluant le joueur
    public void afficherCarte() {
        for (int y = 0; y < hauteur; y++) { // Parcours chaque ligne
            for (int x = 0; x < largeur; x++) { // Parcours chaque colonne
                if (x == joueurX && y == joueurY) {
                    System.out.print("J "); // Affiche 'J' pour le joueur
                } else {
                    System.out.print(map[x][y].getSymbol() + " ");
                }
            }
            System.out.println(); // Retourne à la ligne après chaque ligne de la carte
        }
    }

    private void placerBoutiqueAuCentre() {
        int centreX = largeur / 2;
        int centreY = hauteur / 2;
        BoutiqueArme boutique = new BoutiqueArme();
        map[centreX][centreY] = new BoutiqueTile(boutique);
    }


    private void placerEndTile() {
        int finX = largeur - 1;
        int finY = hauteur - 1;
        map[finX][finY] = new EndTile();
    }

    public void placerJoueurDebut(Personnage joueur) {
        joueurX = 0; // Position X du joueur en haut à gauche
        joueurY = 0; // Position Y du joueur en haut à gauche
        map[joueurX][joueurY] = new JoueurTile(joueur);
    }
    public void resetElements() {
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (map[x][y] instanceof MonstreTile || map[x][y] instanceof ObstacleTile) {
                    map[x][y] = new EmptyTile(); // Réinitialiser les tuiles de monstres et d'obstacles en tuiles vides
                }
            }
        }
    }
    // Méthode pour ajouter des monstres et obstacles aléatoirement
    public void ajouterElementsAleatoires(int nombreMonstres, int nombreObstacles) {
        resetElements();
        ajouterMonstresAleatoires(nombreMonstres);
        ajouterElementsDeType(nombreObstacles, ObstacleTile.class);
    }

    private void ajouterMonstresAleatoires(int nombre) {
        int count = 0;
        while (count < nombre) {
            int x = random.nextInt(largeur);
            int y = random.nextInt(hauteur);

            // Vérifier que la tuile est vide et n'est pas réservée pour le joueur, la boutique ou la case de fin
            if (map[x][y] instanceof EmptyTile && !(x == joueurX && y == joueurY)
                    && !(map[x][y] instanceof BoutiqueTile) && !(map[x][y] instanceof EndTile)) {

                // Créer un type de monstre aléatoire
                Monstre monstre;
                int monstreType = random.nextInt(3); // 0: Orc, 1: Troll, 2: MageNoir
                switch (monstreType) {
                    case 0:
                        monstre = new Orc("Orc");
                        break;
                    case 1:
                        monstre = new Troll("Troll");
                        break;
                    case 2:
                        monstre = new MageNoir("Mage Noir");
                        break;
                    default:
                        monstre = new Orc("Orc"); // Par défaut, un Orc
                }

                map[x][y] = new MonstreTile(monstre); // Placer le monstre sur la carte
                count++;
            }
        }
    }

    private void ajouterElementsDeType(int nombre, Class<? extends Tile> typeClass) {
        int count = 0;
        while (count < nombre) {
            int x = random.nextInt(largeur);
            int y = random.nextInt(hauteur);

            // Vérifier que la tuile est vide et n'est pas réservée pour le joueur, la boutique ou la case de fin
            if (map[x][y] instanceof EmptyTile && !(x == joueurX && y == joueurY)
                    && !(map[x][y] instanceof BoutiqueTile) && !(map[x][y] instanceof EndTile)) {

                if (typeClass == ObstacleTile.class) {
                    map[x][y] = new ObstacleTile(); // Crée un obstacle
                }
                count++;
            }
        }
    }



    public boolean deplacerJoueur(char direction, Personnage joueur) {
        int newX = joueurX;
        int newY = joueurY;

        switch (direction) {
            case 'H': // Haut
                newY--;
                break;
            case 'B': // Bas
                newY++;
                break;
            case 'D': // Droite
                newX++;
                break;
            case 'G': // Gauche
                newX--;
                break;
            default:
                System.out.println("Direction invalide.");
                return false;
        }

        if (newX < 0 || newX >= largeur || newY < 0 || newY >= hauteur) {
            System.out.println("Impossible de sortir des limites de la carte!");
            return false;
        } else if (map[newX][newY] instanceof EmptyTile) {
            map[joueurX][joueurY] = new EmptyTile(); // Laisser l'ancienne position vide
            joueurX = newX;
            joueurY = newY;
            map[joueurX][joueurY] = new JoueurTile(joueur);
            System.out.println("Vous vous êtes déplacé vers " + direction);

            // Vérifier les tuiles adjacentes
            verifierTuilesAdjacentes(joueur);
            return false;
        } else if (map[newX][newY] instanceof EndTile) {
            System.out.println("Félicitations! Vous avez trouvé la sortie et êtes sorti du donjon!");
            return true; // Jeu terminé
        } else {
            System.out.println("Vous ne pouvez pas vous déplacer sur cette tuile.");
            return false;
        }
    }

    // Méthode pour vérifier les tuiles adjacentes (inchangée)
    private void verifierTuilesAdjacentes(Personnage joueur) {
        int[][] directions = {
                {0, -1}, {0, 1}, {-1, 0}, {1, 0}, // N, S, O, E
                {-1, -1}, {1, -1}, {-1, 1}, {1, 1} // diagonales
        };

        for (int[] dir : directions) {
            int adjX = joueurX + dir[0];
            int adjY = joueurY + dir[1];

            if (adjX >= 0 && adjX < largeur && adjY >= 0 && adjY < hauteur) {
                Tile tuileAdjacente = map[adjX][adjY];

                if (tuileAdjacente instanceof MonstreTile) {
                    Monstre monstre = ((MonstreTile) tuileAdjacente).getMonstre();
                    Combat combat = new Combat(joueur, monstre, this,getDirectionFromCoords(dir));
                    String resultat = combat.demarrerCombat();
                    if (resultat.equals("win")) {
                        map[adjX][adjY] = new EmptyTile(); // Monstre vaincu, tuile vide
                    } else {
                        System.out.println("Vous ne pouvez pas continuer, vous devez fuir ou perdre.");
                        return; // Arrêtez de vérifier si le joueur a perdu le combat
                    }
                } else if (tuileAdjacente instanceof BoutiqueTile) {
                    BoutiqueArme boutique = ((BoutiqueTile) tuileAdjacente).getBoutiqueArme();
                    boutique.afficherArmesBoutique();
                    System.out.println("Entrez le numéro de l'arme que vous voulez acheter, ou 0 pour quitter :");

                    Scanner scanner = new Scanner(System.in);
                    int choix = -1; // initialisation en dehors de try-catch pour le tester après

                    try {
                        choix = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Entrée invalide ! Veuillez entrer un nombre.");
                        scanner.nextLine(); // nettoyer le scanner en cas de saisie incorrecte
                    }

                    if (choix > 0) {
                        boutique.acheterArme(joueur, choix);
                    }
                }
            }
        }
    }

    private char getDirectionFromCoords(int[] dir) {
        if (dir[0] == 0 && dir[1] == -1) return 'N';
        if (dir[0] == 0 && dir[1] == 1) return 'S';
        if (dir[0] == -1 && dir[1] == 0) return 'O';
        if (dir[0] == 1 && dir[1] == 0) return 'E';
        return ' ';
    }
    public void fuite(Personnage joueur) {
        int newX = joueurX;
        int newY = joueurY;

        if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur && map[newX][newY] instanceof EmptyTile) {
            map[joueurX][joueurY] = new EmptyTile(); // Laisser l'ancienne position vide
            joueurX = newX;
            joueurY = newY;
            map[joueurX][joueurY] = new JoueurTile(joueur);
            System.out.println("Vous avez fui en reculant.");
        } else {
            System.out.println("Vous n'avez pas trouvé de chemin pour fuir, vous restez sur place !");
        }
    }

    public void sauvegarderCarte(String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(this);  // Sérialise l'objet 'this' (la carte)
            System.out.println("Carte sauvegardée avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de la carte : " + e.getMessage());
        }
    }

    public static Carte chargerCarte(String nomFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            return (Carte) ois.readObject();  // Restaure l'objet Carte à partir du fichier
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la carte : " + e.getMessage());
            return null;  // En cas d'erreur, retourne null
        }
    }

}
