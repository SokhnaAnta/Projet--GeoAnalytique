package exojava.exo5;

public class Vecteur {
    Maillon premier;

    public Vecteur() {
        this.premier = null;

    }

    // fonction nouveau_maillon permettant de créer un nouveau maillon avec des
    // valeurs initiales fournies en arguments
    public Maillon nouveau_maillon(int indice, float valeur) {
        return new Maillon(indice, valeur);
    }

    // fonction PTR vecteur_creux (float t[ ], int n) qui prend un tableau t ayant n
    // éléments et construit sa représentation sous forme de vecteur creux.
    public Vecteur vecteur_creux(float[] t, int n) {
        Vecteur vecteurCreux = new Vecteur();
        for (int i = 0; i < n; i++) {
            if (t[i] != 0) {
                Maillon nouveauMaillon = nouveau_maillon(i, t[i]);
                if (vecteurCreux.premier == null) {
                    vecteurCreux.premier = nouveauMaillon;
                } else {
                    Maillon courant = vecteurCreux.premier;
                    while (courant.suivant != null) {
                        courant = courant.suivant;
                    }
                    courant.suivant = nouveauMaillon;
                }
            }
        }
        return vecteurCreux;

    }

    // Ecrire une fonction PTR somme (PTR a, PTR b) qui reçoit deux vecteurs creux a
    // et b et retourne le vecteur creux qui représente leur somme (c’est-à-dire
    // l’addition des deux vecteurs, composante par composante).

    public Vecteur somme(Vecteur autreVecteur) {
        Vecteur resultat = new Vecteur();
        Maillon courantThis = this.premier;
        Maillon courantAutre = autreVecteur.premier;
        Maillon dernierResultat = null;

        // Parcours des deux vecteurs
        while (courantThis != null && courantAutre != null) {
            int nouvelIndice;
            float nouvelleValeur;

            if (courantThis.indice < courantAutre.indice) {
                nouvelIndice = courantThis.indice;
                nouvelleValeur = courantThis.valeur;
                courantThis = courantThis.suivant;
            } else if (courantThis.indice > courantAutre.indice) {
                nouvelIndice = courantAutre.indice;
                nouvelleValeur = courantAutre.valeur;
                courantAutre = courantAutre.suivant;
            } else {
                nouvelIndice = courantThis.indice;
                nouvelleValeur = courantThis.valeur + courantAutre.valeur;
                courantThis = courantThis.suivant;
                courantAutre = courantAutre.suivant;
            }

            // Ajout du nouveau maillon au résultat si le nouveau n'est pas null(vecteur
            // creux )
            if (nouvelleValeur != 0) {
                Maillon nouveauMaillon = nouveau_maillon(nouvelIndice, nouvelleValeur);
                if (resultat.premier == null) {
                    resultat.premier = nouveauMaillon;
                } else {
                    dernierResultat.suivant = nouveauMaillon;
                }
                dernierResultat = nouveauMaillon;
            }
        }

        // Ajout des éléments restants du premier vecteur
        while (courantThis != null) {
            Maillon nouveauMaillon = nouveau_maillon(courantThis.indice, courantThis.valeur);
            dernierResultat.suivant = nouveauMaillon;
            dernierResultat = nouveauMaillon;
            courantThis = courantThis.suivant;
        }

        // Ajout des éléments restants du deuxième vecteur
        while (courantAutre != null) {
            Maillon nouveauMaillon = nouveau_maillon(courantAutre.indice, courantAutre.valeur);
            dernierResultat.suivant = nouveauMaillon;
            dernierResultat = nouveauMaillon;
            courantAutre = courantAutre.suivant;
        }

        return resultat;
    }

    // on teste toutt caa
    // fonction pour afficher
    public static void afficherVecteur(Vecteur vecteur) {
        Maillon courant = vecteur.premier;
        while (courant != null) {
            System.out.println("Indice : " + courant.indice + ", Valeur : " + courant.valeur);
            courant = courant.suivant;
        }
    }

    public static void main(String[] args) {

        float[] tableau1 = { 0, -1, 0, 2, 0, 3 };
        float[] tableau2 = { 0, 1, 4, 0, 5, 0, 6 };

        // Création de deux vecteurs creux
        Vecteur vecteur1 = new Vecteur().vecteur_creux(tableau1, tableau1.length);
        Vecteur vecteur2 = new Vecteur().vecteur_creux(tableau2, tableau2.length);

        // Affichage des vecteurs creux
        System.out.println("Vecteur 1 : ");
        afficherVecteur(vecteur1);
        System.out.println("\nVecteur 2 : ");
        afficherVecteur(vecteur2);

        // Calcul de la somme des vecteurs creux
        Vecteur sommeVecteurs = vecteur1.somme(vecteur2);

        // Affichage du résultat
        System.out.println("\nSomme des vecteurs : ");
        afficherVecteur(sommeVecteurs);
    }
}
