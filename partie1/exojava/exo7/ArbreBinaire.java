package exojava.exo7;

public class ArbreBinaire {
    Noeud racine;

    public ArbreBinaire() {
        racine = null;
    }

    // Méthode pour afficher les étiquettes des feuilles de l'arbre
    public void feuilles() {
        if (racine == null)
            return;
        feuilles(racine);
    }

    private void feuilles(Noeud n) {
        if (n == null)
            return;
        // le noeud est il une feuille ?
        if (n.gauche == null && n.droit == null)
            System.out.print(n.valeur + " ");
        feuilles(n.gauche);
        feuilles(n.droit);
    }

    // Méthode pour afficher les degrés des nœuds de l'arbre
    public void degre() {
        if (racine == null)
            return;
        degre(racine);
    }

    private void degre(Noeud n) {
        if (n == null)
            return;

        // Calcul du degré
        int deg = 0;
        if (n.gauche != null)
            deg++; // Incrémenter le degré si le nœud a un fils gauche
        if (n.droit != null)
            deg++; // Incrémenter le degré si le nœud a un fils droit

        // Affichage du degré du nœud
        System.out.println("Noeud " + n.valeur + " : degré " + deg);

        // Appels récursifs pour les fils
        degre(n.gauche);
        degre(n.droit);

    }

    // Méthode pour afficher la profondeur d'un nœud x
    public void profondeur(Noeud x) {
        if (racine == null || x == null)
            return;
        int prof = profondeur(racine, x, 0);
        System.out.println("Profondeur de " + x.valeur + " : " + prof);
    }

    private int profondeur(Noeud n, Noeud x, int niveau) {
        if (n == null)
            return -1;
        if (n == x)
            return niveau;
        int gauche = profondeur(n.gauche, x, niveau + 1);
        int droit = profondeur(n.droit, x, niveau + 1);
        return Math.max(gauche, droit);
    }

    // Méthode pour calculer la hauteur de l'arbre
    public int hauteur() {
        return hauteur(racine);
    }

    private int hauteur(Noeud n) {
        if (n == null)
            return -1;
        int gauche = hauteur(n.gauche);
        int droit = hauteur(n.droit);
        return Math.max(gauche, droit) + 1;
    }

    // Méthode pour calculer la somme des valeurs des nœuds de l'arbre
    public int sommeNoeuds() {
        return sommeNoeuds(racine);
    }

    private int sommeNoeuds(Noeud n) {
        if (n == null)
            return 0;
        return n.valeur + sommeNoeuds(n.gauche) + sommeNoeuds(n.droit);
    }

    public static void main(String[] args) {
        // Construction de l'arbre binaire
        ArbreBinaire arbre = new ArbreBinaire();
        arbre.racine = new Noeud(1);
        arbre.racine.gauche = new Noeud(2);
        arbre.racine.droit = new Noeud(3);
        arbre.racine.gauche.gauche = new Noeud(4);
        arbre.racine.gauche.droit = new Noeud(5);
        arbre.racine.droit.gauche = new Noeud(6);
        // Pour changer un peu
        // arbre.racine.droit.droit = new Noeud(7);

        // Test des différentes méthodes
        System.out.print("Feuilles de l'arbre : ");
        arbre.feuilles();
        System.out.println();

        System.out.println("Degrés des nœuds de l'arbre : ");
        arbre.degre();

        Noeud x = arbre.racine.gauche;
        arbre.profondeur(x);

        System.out.println("Hauteur de l'arbre : " + arbre.hauteur());

        System.out.println("Somme des valeurs des nœuds de l'arbre : " + arbre.sommeNoeuds());
    }
}
