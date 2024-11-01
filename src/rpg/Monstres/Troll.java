package rpg.Monstres;

import rpg.Armes.Arme;
import rpg.Inventaire;

public class Troll extends Monstre {
    public Troll(String nom) {
        super("Troll", 40, 15, 60, 10, 100, new Arme("Marteau Géant", 20, 10, "Commun", 0), new Inventaire());
    }
}
