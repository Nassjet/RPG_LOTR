package rpg.Monstres;

import rpg.Armes.Arme;
import rpg.Inventaire;

public class Orc extends Monstre{
    public Orc(String nom) {
        super("Orc",30,10,50,20,100,new Arme("Massue", 15, 5, "Commun", 0), new Inventaire());
    }
}
