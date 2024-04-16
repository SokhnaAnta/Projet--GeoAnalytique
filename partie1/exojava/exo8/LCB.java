package exojava.exo8;

public class LCB {
    Maillon tete;
    Maillon queue;

    public LCB() {
        this.tete = null;
        this.queue = null;
    }
    // 2 Fonction ajout_numero(int num, LCB numeros):

    public LCB ajout_numero(int num) {
        Maillon nouveau = new Maillon(num);
        if (tete == null) {
            tete = nouveau;
            queue = nouveau;
        } else {
            queue.suiv = nouveau;
            nouveau.prec = queue;
            queue = nouveau;
        }
        return this;
    }
}
