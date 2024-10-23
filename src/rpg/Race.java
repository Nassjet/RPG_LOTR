package rpg;

import rpg.Armes.Arme;

public abstract class Race extends Personnage {

    // Le constructeur de Race appelle celui de la classe parente Personnage
    public Race(String nom, int attack, int defense, int endurance, int vitesse, double health, Arme armeEquipee, Inventaire inventaire,int cash) {
        super(nom, attack, defense, endurance, vitesse, health, armeEquipee, inventaire,cash);
    }

    public abstract void afficherInfosRace(); // Par exemple, chaque race peut avoir une méthode pour afficher ses caractéristiques.

}
