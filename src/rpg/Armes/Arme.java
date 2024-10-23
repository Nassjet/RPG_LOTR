package rpg.Armes;

import rpg.Personnage;

public class Arme {
    public String name;
    public int degats;
    public int poids;
    public String rarete;
    public int prix;

    public Arme (String name, int degats, int poids, String rarete, int prix) {
        this.name = name;
        this.degats = degats;
        this.poids = poids;
        this.rarete = rarete;
        this.prix = prix;
    }
    public int calculerDegatsTotaux(Personnage personnage) {
        return personnage.getAttack() + this.degats;
    };

    public String getName() {
        return this.name;
    }

    public int getDegats() {
        return this.degats;
    }

    public int getPrix() {
        return this.prix;
    }
}