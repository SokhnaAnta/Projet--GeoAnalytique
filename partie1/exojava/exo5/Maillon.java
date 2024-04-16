package exojava.exo5;

public class Maillon {
    int indice;
    float valeur;
    Maillon suivant;

    public Maillon(int indice, float valeur) {
        this.indice = indice;
        this.valeur = valeur;
        this.suivant = null;
    }
}
