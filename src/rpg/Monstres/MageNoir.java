package rpg.Monstres;

import rpg.Armes.Arme;
import rpg.Inventaire;

public class MageNoir extends Monstre {
    public MageNoir(String nom) {
        super("Mage Noir", 25, 5, 40, 30, 150, new Arme("Bâton Maléfique", 10, 2, "Rare", 0), new Inventaire());
    }
}
