package rpg.Races;

import rpg.Armes.Arme;
import rpg.Inventaire;
import rpg.Race;

public class Hobbit extends Race {
    public Hobbit(String nom, Arme armeEquipee) {
        // Les Hobbits ont des stats spécifiques qui sont fixées ici
        super(nom, 30, 20, 40, 25, 100.0, armeEquipee, new Inventaire(), 100);
    }

    // Implémentation de la méthode abstraite de Race
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

    // Tu peux ajouter ici des méthodes spécifiques à la race Races.Hobbit si nécessaire.
}
