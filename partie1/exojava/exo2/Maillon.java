package exojava.exo2;

public class Maillon {
    String info;
    Maillon suivant;
    Maillon precedent;

    public Maillon(String info) {
        this.info = info;
        this.suivant = null;
        this.precedent = null;
    }
}
