package rpg;

import rpg.Armes.Arme;

public interface ObjetInventaire {
    public void ajouterArme(Arme arme);
    public void supprimerArme(Arme arme);
    public void afficherArmes(Personnage personnage);
}
