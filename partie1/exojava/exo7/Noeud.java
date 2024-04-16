package exojava.exo7;

public class Noeud {
    int valeur;
    Noeud gauche;
    Noeud droit;

    public Noeud(int valeur) {
        this.valeur = valeur;
        gauche = null;
        droit = null;
    }
}
