package rpg;

import rpg.Monstres.Monstre;

// Classe Tile abstraite pour représenter une case de la carte
public abstract class Tile {
    public abstract char getSymbol(); // Permet de représenter le type de tile (affichage)
}

// Tile pour représenter la position du joueur
class JoueurTile extends Tile {
    private Personnage joueur;

    public JoueurTile(Personnage joueur) {
        this.joueur = joueur;
    }

    @Override
    public char getSymbol() {
        // Retourne la première lettre du nom du joueur, en majuscule
        return joueur.getNom().toUpperCase().charAt(0);
    }
}

// Tile pour représenter un monstre
class MonstreTile extends Tile {
    private Monstre monstre;

    public MonstreTile(Monstre monstre) {
        this.monstre = monstre;
    }

    @Override
    public char getSymbol() {
        return 'M'; // Par exemple, "M" pour monstre
    }

    public Monstre getMonstre() {
        return monstre;
    }
}

class BoutiqueTile extends Tile {
    private BoutiqueArme boutiqueArme;

    public BoutiqueTile(BoutiqueArme boutiqueArme) {
        if (boutiqueArme == null) {
            throw new IllegalArgumentException("BoutiqueArme ne peut pas être null");
        }
        this.boutiqueArme = boutiqueArme;
    }

    @Override
    public char getSymbol() {
        return 'B'; // Par exemple, "B" pour boutique
    }

    public BoutiqueArme getBoutiqueArme() {
        return boutiqueArme;
    }
}


// Tile pour représenter un obstacle
class ObstacleTile extends Tile {
    @Override
    public char getSymbol() {
        return 'O'; // Par exemple, "O" pour obstacle
    }
}

// Tile vide
class EmptyTile extends Tile {
    @Override
    public char getSymbol() {
        return '.'; // Par exemple, "." pour une tile vide
    }
}

class EndTile extends Tile {
    @Override
    public char getSymbol() {
        return 'E';
    }
}
