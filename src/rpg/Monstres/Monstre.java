package rpg.Monstres;

import rpg.Armes.Arme;
import rpg.Inventaire;
import rpg.Personnage;

public abstract class Monstre extends Personnage {

    public Monstre(String nom, int attack, int defense, int endurance, int vitesse, double health, Arme armeEquipee, Inventaire inventaire) {
        super(nom, attack, defense, endurance, vitesse, health, armeEquipee, inventaire, 0);
    }

}
