package exojava.exo1;

public class Maillon {
    String mot;
    Maillon suivant;

    public Maillon(String mot) {
        this.mot = mot;
        this.suivant = null;
    }
}
