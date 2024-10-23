package rpg;

import rpg.Armes.Arme;

public abstract class Personnage {
    private String nom;
    private int attack,defense,endurance,vitesse,cash;
    private double health;

    public Arme armeEquipee;
    public Inventaire inventaire = new Inventaire();

    public Personnage(String nom, int attack, int defense, int endurance,int vitesse, double health, Arme armeEquipee,Inventaire inventaire, int cash) {
        this.nom = nom;
        this.attack = attack;
        this.defense = defense;
        this.endurance = endurance;
        this.health = health;
        this.vitesse = vitesse;
        this.armeEquipee = armeEquipee;
        this.inventaire = new Inventaire();
        this.cash = cash;
    }



    public int getAttack() {
        return attack;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public Arme getArmeEquipee() {
        return armeEquipee;
    }

    public void setArmeEquipee(Arme armeEquipee) {
        this.armeEquipee = armeEquipee;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void getStats () {
        System.out.println("Nom: " + nom);
        System.out.println("Attaque: " + attack);
        System.out.println("Défense: " + defense);
        System.out.println("Endurance: " + endurance);
        System.out.println("Vitesse: " + vitesse);
        System.out.println("Points de vie: " + health);
        System.out.println("Arme équipée: " + armeEquipee.name);
        System.out.println("Dégâts de l'arme: " + armeEquipee.degats);
        System.out.println("Vous avez:" + cash + "pièces");
    }

    public void afficherInventaire() {
        this.inventaire.afficherArmes();
    }

    public void depenserCash(int montant){
        if (cash >= montant) {
            cash -= montant;
        }
        else {
            System.out.println("Vous n'avez pas assez de pièces d'or ");
        }
    }
}
