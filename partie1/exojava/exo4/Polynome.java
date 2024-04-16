package exojava.exo4;

public class Polynome {
    Terme premier;

    // Méthode pour ajouter un terme au polynôme
    public void ajouterTerme(int coefficient, int exposant) {
        Terme nouveauTerme = new Terme(coefficient, exposant);
        if (premier == null) {
            premier = nouveauTerme;
        } else {
            Terme courant = premier;
            while (courant.suivant != null) {
                courant = courant.suivant;
            }
            courant.suivant = nouveauTerme;
        }
    }

    // Méthode pour afficher le polynôme
    public void afficherPolynome() {
        Terme courant = premier;
        while (courant != null) {
            System.out.print(courant.coefficient + "x^" + courant.exposant);
            if (courant.suivant != null) {
                System.out.print(" + ");
            }
            courant = courant.suivant;
        }
        System.out.println();
    }

    // fonction DERIVEE(P) qui reçoit en entrée un polynôme et donne en sortie la
    // dérivée P’ de ce polynôme
    public Polynome derivee() {
        Polynome derivee = new Polynome();
        Terme courant = premier;
        while (courant != null) {
            if (courant.exposant > 0) {
                int nouveauCoefficient = courant.coefficient * courant.exposant;
                int nouvelExposant = courant.exposant - 1;
                derivee.ajouterTerme(nouveauCoefficient, nouvelExposant);
            }
            courant = courant.suivant;
        }
        return derivee;
    }

    // Méthode pour calculer la k-ième dérivée du polynôme
    public Polynome deriveekieme(int k) {
        Polynome derivee_k = this;
        for (int i = 0; i < k; i++) {
            derivee_k = derivee_k.derivee();
        }
        return derivee_k;

    }

    public static void main(String[] args) {
        Polynome polynome = new Polynome();
        polynome.ajouterTerme(3, 2);
        polynome.ajouterTerme(4, 1);
        polynome.afficherPolynome();
        // polynome.derivee().afficherPolynome();
        polynome.deriveekieme(2).afficherPolynome();
    }

}
