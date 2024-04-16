package exojava.exo2;

public class Listechaineebi {
    Maillon premier;
    Maillon dernier;

    // qui crée un nouveau maillon associé à la chaîne s que jai nommé info et
    // l’ajoute en tête de la
    // liste.
    public Maillon ajouter_devant(String info) {
        Maillon nouveauMaillon = new Maillon(info);// cree un new maillon
        nouveauMaillon.suivant = premier;
        if (premier != null) {
            premier.precedent = nouveauMaillon;
        } else {// cest a dire si le maillon est vide
            dernier = nouveauMaillon;// le dernier est aussi le premier(on a un seul element)
        }
        premier = nouveauMaillon;
        return premier;
    }

    // Réécrire la fonction ajouter_devant en supposant que premier et dernier sont
    // des paramètres de la fonction, non des variables globales.
    public Maillon ajouter_devant(Maillon premier, Maillon dernier, String info) {
        Maillon nouveauMaillon = new Maillon(info);
        nouveauMaillon.suivant = premier;
        if (premier != null) {
            premier.precedent = nouveauMaillon;
        } else {
            dernier = nouveauMaillon;
        }
        premier = nouveauMaillon;
        return premier;

    }

    // qui supprime de la LCB le premier maillon portant l’information représentée
    // par s, s’il existe. Cette fonction accède aux variables globales
    // premier et dernier. Notez que l’existence d’un double chaînage permet de
    // parcourir la liste avec un seul pointeur

    public Maillon supprimer(String info) {
        Maillon courant = premier;
        while (courant != null && !courant.info.equals(info)) {
            courant = courant.suivant;
        }
        // on sort de la boucle si on parcours tout sans trouvé info ou si on a trouve
        // l'info

        // dans ce if on a trouvé info
        if (courant != null) {
            // trouvé des le debut
            if (premier == courant) {
                premier = courant.suivant;
                // trouvé des le debut mais on a un seul element
                if (premier == null) {
                    dernier = null;
                } else {// trouvé des le debut mais on a pas un seul element
                    premier.precedent = null;
                }
            } else {
                // trouve au milieu ou a la fin
                courant.precedent.suivant = courant.suivant;
                // si trouvé en dernier
                if (courant.suivant == null) {
                    dernier = courant.precedent;
                } else {
                    courant.suivant.precedent = courant.precedent;
                }

            }

        }
        return premier;

    }

    // methode pour afficher les listes
    public void afficherListe() {
        Maillon courant = premier;
        System.out.print("Liste : ");
        while (courant != null) {
            System.out.print(courant.info + " ");
            courant = courant.suivant;
        }
        System.out.println();
    }

    // ici on a les test
    public static void main(String[] args) {
        Listechaineebi lcb = new Listechaineebi();
        // test de ajouter_devant
        System.out.println("Ajout de quelques mots au début de la liste :");
        lcb.ajouter_devant("khadija");
        lcb.ajouter_devant("enfant");
        lcb.ajouter_devant("sage");
        lcb.ajouter_devant("askip");
        lcb.afficherListe();
        lcb.supprimer("asbkip");
        lcb.afficherListe();

    }
}
