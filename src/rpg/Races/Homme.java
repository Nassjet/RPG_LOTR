package rpg.Races;
import rpg.Armes.Arme;
import rpg.Inventaire;
import rpg.Race;

public class Homme extends Race {

    public Homme(String nom, Arme armeEquipee) {
        super(nom, 60, 30, 40, 35, 120.0, armeEquipee, new Inventaire(), 100);
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

    // Tu peux ajouter des méthodes spécifiques à la race Races.Homme ici si nécessaire
}
