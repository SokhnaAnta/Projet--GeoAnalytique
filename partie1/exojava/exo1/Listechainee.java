package exojava.exo1;

public class Listechainee {

    Maillon tete;

    // qui crée un maillon pour mot, le rajoute en tête de la liste chaînée L, et
    // retourne la nouvelle liste.
    public Maillon ajoute_debut(String mot) {
        Maillon nouveauMaillon = new Maillon(mot);
        nouveauMaillon.suivant = tete;
        tete = nouveauMaillon;
        return tete;
    }

    // qui crée un maillon pour mot, le rajoute en fin de la liste chaînée L, et
    // retourne la nouvelle liste.
    public Maillon ajoute_fin(String mot) {
        Maillon nouveauMaillon = new Maillon(mot);
        Maillon courant = tete;
        if (tete == null) {
            tete = nouveauMaillon;
            return tete;
        }
        while (courant.suivant != null) {
            // parcourir pour recup le dernier
            courant = courant.suivant;
        }
        // apres avoir recup le dernier on le met à la fin
        courant.suivant = nouveauMaillon;
        return tete;
    }

    // 3. PTR supprimer(char *mot, PTR l) qui supprime le maillon qui contient le
    // mot considéré dans la liste L et retourne la nouvelle liste.
    public Maillon supprimer(String mot) {
        Maillon courant = tete;
        Maillon precedent = null;
        while (courant != null) {
            if (courant.mot.equals(mot)) {
                if (precedent == null) {// c'est à dire le premier
                    // on supprime donc le premier
                    tete = tete.suivant;
                } else { // precedent different de null
                    precedent.suivant = courant.suivant;
                }
            } else {// si le mot n'est pas egal a la valeur du maillon courant(pour supprimer tout
                    // les mots)
                precedent = courant;
            }
            // et la on avance
            courant = courant.suivant;

        }
        return tete;
    }

    // 4. void premiers( PTR liste, int n) qui affiche les n premiers mots de la
    // liste
    public void premiers(int n) {
        Maillon courant = tete;
        int tmp = 1;
        while (courant != null & tmp <= n) {
            System.out.println(courant.mot);
            courant = courant.suivant;
            tmp += 1;
        }

    }

    // trier
    public Maillon sort() {
        if (tete == null || tete.suivant == null) {
            return tete; // Si la liste est vide ou n'a qu'un élément, elle est déjà triée
        }

        // Algorithme de tri par insertion
        Maillon trie = null;
        Maillon courant = tete;

        while (courant != null) {
            Maillon suivant = courant.suivant;

            if (trie == null || courant.mot.compareTo(trie.mot) < 0) {
                courant.suivant = trie;
                trie = courant;
            } else {
                Maillon temp = trie;
                while (temp.suivant != null && courant.mot.compareTo(temp.suivant.mot) > 0) {
                    temp = temp.suivant;
                }
                courant.suivant = temp.suivant;
                temp.suivant = courant;
            }
            courant = suivant;
        }

        return trie;
    }

    // 5. void purifie(MAILLON *liste) qui ne conserve dans liste qu’une seule
    // occurrence d’un mot représenté plusieurs fois dans la liste chaînée (meune
    // ngen ko teube)
    public void purifie_surliste_nontrie() {
        Maillon courant = tete;
        while (courant != null) {
            Maillon suivant = courant.suivant;
            Maillon precedent = courant;
            while (suivant != null) {
                if (suivant.mot.equals(courant.mot)) {
                    // on supprime le maillon suivant
                    precedent.suivant = suivant.suivant;
                } else {
                    // si ce n'est pas egal on avance
                    precedent = suivant;
                }
                // parcourir la liste
                suivant = suivant.suivant;
            }
            // passer au maillon suivant
            courant = courant.suivant;
        }
    }

    // 5. void purifie(MAILLON *liste) qui ne conserve dans liste qu’une seule
    // occurrence d’un mot représenté plusieurs fois dans la liste chaînée triée
    public void purifie() {
        if (tete == null || tete.suivant == null) {
            return; // Si la liste est vide ou n'a qu'un seul élément, elle est déjà purifiée
        }

        Maillon courant = tete;
        while (courant.suivant != null) {
            if (courant.mot.equals(courant.suivant.mot)) {
                courant.suivant = courant.suivant.suivant; // Supprime le maillon suivant s'il contient le même mot que
                                                           // le maillon courant
            } else {
                courant = courant.suivant; // Avance dans la liste
            }
        }
    }

    // Méthode pour afficher la liste
    public void afficherListe() {
        Maillon courant = tete;
        System.out.print("Liste : ");
        while (courant != null) {
            System.out.print(courant.mot + " ");
            courant = courant.suivant;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Listechainee liste = new Listechainee();

        // Test de la fonction ajouteDebut
        System.out.println("Ajout de quelques mots au début de la liste :");
        liste.ajoute_debut("chien");
        liste.ajoute_debut("chat");
        liste.ajoute_debut("chat");
        liste.ajoute_debut("oiseau");
        liste.ajoute_debut("chat");
        liste.ajoute_debut("chien");
        liste.ajoute_debut("chat");

        System.out.println("Affichage de la liste mise à jour avec ajout au debut");
        // Affichage de la liste mise à jour
        liste.afficherListe();
        System.out.println("Liste aprés ajout à la fin de la liste");
        liste.ajoute_fin("souris");
        liste.ajoute_fin("verte");

        liste.afficherListe();
        System.out.println("liste apres suppresion du mot chien");
        liste.supprimer("chien");
        liste.afficherListe();
        System.out.println("Liste aprés trie");
        liste.sort();
        liste.afficherListe();
        System.out.println("Liste aprés purification");
        liste.purifie();
        liste.afficherListe();
        System.out.println("Liste aprés purification");

        liste.afficherListe();
        // liste.sort();
        // liste.afficherListe();
        // liste.purifie();
        // liste.afficherListe();

        System.out.println("liste apres affichage des 3 premiers ");
        liste.premiers(3);

    }

}
