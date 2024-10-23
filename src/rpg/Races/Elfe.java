package rpg.Races;

import rpg.Armes.Arme;
import rpg.Inventaire;
import rpg.Race;

public class Elfe extends Race {

    public Elfe(String nom, Arme armeEquipee) {
        super(nom, 55, 25, 30, 50, 110.0, armeEquipee, new Inventaire(), 100);
    }

    @Override
    public void afficherInfosRace() {
        System.out.println("Race: Races.Hobbit");
        System.out.println("Nom: " + getNom());
        System.out.println("Attaque: " + getAttack());
        System.out.println("Défense: " + getDefense());
        System.out.println("Endurance: " + getEndurance());
        System.out.println("Vitesse: " + getVitesse());
        System.out.println("Points de vie: " + getHealth());
        System.out.println("Arme équipée: " + getArmeEquipee().name);
    }

    // Tu peux ajouter des méthodes spécifiques à la race Elfe ici si nécessaire
}
