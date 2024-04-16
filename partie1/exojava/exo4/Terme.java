package exojava.exo4;

public class Terme {
    int coefficient;
    int exposant;
    Terme suivant;

    public Terme(int coefficient, int exposant) {
        this.coefficient = coefficient;
        this.exposant = exposant;
        this.suivant = null;
    }

}
