package exojava.exo3;

import java.util.Scanner;

public class Listechainee {
    Maillon tete;

    // ajouter_debut
    public void ajouter_debut(int n) {
        Maillon nouveauMaillon = new Maillon(n);
        nouveauMaillon.suivant = tete;
        tete = nouveauMaillon;
    }

    // 1 Créer une liste avec dix valeurs saisies au clavier
    public void creerListe() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir 10 entiers ");
        for (int i = 0; i < 10; i++) {
            int entier = scanner.nextInt();
            ajouter_debut(entier);
        }
        scanner.close();
    }

    // Tester l'égalité de deux listes
    public boolean tester_egalite(Listechainee autreListe) {
        Maillon courant = this.tete;
        Maillon autreL = autreListe.tete;

        while (courant != null && autreL != null) {
            if (courant.n != autreL.n) {
                return false;
            } else {
                courant = courant.suivant;
                autreL = autreL.suivant;
            }

        }
        return courant == null && autreL == null;

    }

    // mETHODE POUR Concaténer deux listes :dans une troisieme liste
    public Listechainee concatener_deux_liste(Listechainee autreListe) {
        Listechainee listeconcatener = new Listechainee();
        Listechainee listeInverser = new Listechainee();

        // on copie la 1er liste
        Maillon courant = this.tete;
        while (courant != null) {
            listeInverser.ajouter_debut(courant.n);
            courant = courant.suivant;
        }
        // on copie la 2eme liste
        courant = autreListe.tete;
        while (courant != null) {
            listeInverser.ajouter_debut(courant.n);
            courant = courant.suivant;
        }
        while (listeInverser.tete != null) {
            listeconcatener.ajouter_debut(listeInverser.tete.n);
            listeInverser.tete = listeInverser.tete.suivant;
        }
        return listeconcatener;

    }

    // Methode pour concatener sans 3eme liste
    public void concatener(Listechainee autreListe) {
        if (this.tete == null) {
            this.tete = autreListe.tete;
        } else {
            Maillon courant = this.tete;
            while (courant.suivant != null) {
                courant = courant.suivant;

            }
            courant.suivant = autreListe.tete;

        }

    }

    // Méthode pour afficher la liste
    public void afficherListe() {
        Maillon courant = tete;
        System.out.print("Liste : ");
        while (courant != null) {
            System.out.print(courant.n + " ");
            courant = courant.suivant;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Listechainee liste = new Listechainee();
        Listechainee autreliste = new Listechainee();
        // liste.creerListe();
        liste.ajouter_debut(3);
        liste.ajouter_debut(2);
        liste.ajouter_debut(1);
        autreliste.ajouter_debut(6);
        autreliste.ajouter_debut(5);
        autreliste.ajouter_debut(4);
        liste.afficherListe();
        autreliste.afficherListe();
        // System.out.println(liste.tester_egalite(autreliste));
        Listechainee listeconcatener = liste.concatener_deux_liste(autreliste);
        listeconcatener.afficherListe();
        liste.concatener(autreliste);
        liste.afficherListe();

    }

}
