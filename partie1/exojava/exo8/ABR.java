package exojava.exo8;

public class ABR {

    String nom;
    LCB numeros;
    ABR gauche;
    ABR droit;

    public ABR(String nom, LCB numeros) {
        this.nom = nom;
        this.numeros = numeros;
        this.gauche = null;
        this.droit = null;
    }

    // 3 Fonction pour insérer un nouveau nom propre et ses numéros de page dans un
    // ABR
    public ABR ajout_nompropre(String nom, int[] t, ABR a) {
        if (a == null) {
            LCB numeros = new LCB();
            for (int num : t) {
                numeros = numeros.ajout_numero(num);
            }
            return new ABR(nom, numeros);
        }
        if (nom.compareTo(a.nom) < 0) {
            // si le nom a inserer est plus inferieur
            a.gauche = ajout_nompropre(nom, t, a.gauche);
        } else if (nom.compareTo(a.nom) > 0) {
            a.droit = ajout_nompropre(nom, t, a.droit);
        }
        return a;
    }

    // 4 Fonction pour supprimer un numéro de page pour un nom donné dans un ABR
    public ABR supprimer_numero(String nom, int numero, ABR a) {
        // Vérifier si l'arbre est vide
        if (a == null) {
            return null;
        }

        // Si le nom correspond au nom du nœud actuel
        if (nom.equals(a.nom)) {
            // supprimer le numéro de la liste des numéros du nœud actuel
            a.numeros = supprimer_numero_liste(a.numeros, numero);
        } else if (nom.compareTo(a.nom) < 0) { // Si le nom est inférieur, rechercher dans le sous-arbre gauche
            a.gauche = supprimer_numero(nom, numero, a.gauche);
        } else { // Sinon, rechercher dans le sous-arbre droit
            a.droit = supprimer_numero(nom, numero, a.droit);
        }
        return a;
    }

    private LCB supprimer_numero_liste(LCB numeros, int numero) {
        // Vérifier si la liste est vide
        if (numeros == null) {
            return null;
        }

        // Parcourir la liste des numéros
        Maillon courant = numeros.tete;
        while (courant != null) {
            // Si le numéro est trouvé dans la liste
            if (courant.numero == numero) {
                // Mettre à jour les pointeurs précédent et suivant pour exclure le numéro de la
                // liste
                if (courant.prec == null) { // Si le numéro est en tête de liste
                    numeros.tete = courant.suiv;
                } else {
                    courant.prec.suiv = courant.suiv;
                }
                if (courant.suiv == null) { // Si le numéro est en fin de liste
                    numeros.queue = courant.prec;
                } else {
                    courant.suiv.prec = courant.prec;
                }
                return numeros;
            }
            courant = courant.suiv; // Passer au maillon suivant
        }
        return numeros; // Retourner la liste inchangée si le numéro n'est pas trouvé
    }

    /*
     * Fatou, Mamadou, Oussenou, Pierre, Soda.
     * -----Fatou
     * ---------------\
     * ----------------Mamadou
     * -----------------\
     * ------------------Ousseynou
     * -------------------\
     * -------------------Pierre
     * --------------------\
     * ---------------------Soda
     * 
     */
    // Fonction pour afficher l'index selon l'ordre alphabétique des noms
    public void afficher_index(ABR a) {
        // Vérifier si le nœud actuel n'est pas nul
        if (a != null) {
            // Afficher les noms du sous-arbre gauche
            afficher_index(a.gauche);

            // Afficher le nom du nœud actuel
            System.out.print(a.nom + ": ");

            // Afficher les numéros de page associés au nom du nœud actuel
            afficher_numeros(a.numeros);
            System.out.println(); // Aller à la ligne

            // Afficher les noms du sous-arbre droit
            afficher_index(a.droit);
        }
    }

    // Méthode pour afficher les numéros de page associés à un nom propre
    private void afficher_numeros(LCB numeros) {
        // Vérifier si la liste de numéros n'est pas nulle
        if (numeros != null) {
            // Initialiser un pointeur courant au début de la liste
            Maillon courant = numeros.tete;
            // Parcourir la liste des numéros de page
            while (courant != null) {
                // Afficher le numéro de page
                System.out.print(courant.numero + " ");
                // Passer au maillon suivant dans la liste
                courant = courant.suiv;
            }
        }
        /*
         * La principale raison pour laquelle les performances de la recherche dans
         * l'ABR ne sont pas meilleures que celles d'une liste chaînée simple est que
         * dans le pire des cas, l'ABR peut se comporter comme une liste chaînée droite,
         * ce qui entraîne une complexité de recherche de O(n), où n est le nombre total
         * de nœuds dans l'arbre.
         * 
         * Dans un ABR équilibré, la complexité de la recherche serait logarithmique,
         * avec une complexité de O(log n). Cependant, si les éléments sont insérés dans
         * l'arbre dans un ordre non optimal (par exemple, dans un ordre déjà trié),
         * l'arbre pourrait devenir déséquilibré, réduisant ainsi son efficacité.
         * 
         * Pour améliorer les performances de recherche dans un ABR, voici quelques
         * solutions possibles :
         * 
         * Équilibrer l'arbre : Après chaque opération d'insertion ou de suppression,
         * rééquilibrer l'arbre pour maintenir une structure équilibrée. Cela peut être
         * fait en utilisant des techniques telles que la rotation et la réorganisation
         * des nœuds.
         * 
         * Utiliser une autre structure de données : Si la recherche est la principale
         * opération effectuée sur les données et que l'insertion et la suppression sont
         * moins fréquentes, une structure de données différente comme une table de
         * hachage peut être plus appropriée.
         * 
         * Optimiser l'ordre d'insertion : Insérer les éléments dans l'ABR d'une manière
         * qui maintient l'équilibre de l'arbre peut également améliorer les
         * performances. Par exemple, utiliser un algorithme d'insertion équilibré comme
         * l'insertion AVL ou l'insertion de l'arbre rouge-noir peut aider à éviter la
         * formation d'un arbre déséquilibré.
         * 
         * Utiliser des structures de données alternatives pour des cas spécifiques :
         * Dans certains cas, une liste chaînée simple peut être plus efficace pour les
         * opérations de recherche, surtout si les données sont déjà triées et ne
         * nécessitent pas de rééquilibrage constant d'un ABR.
         */
    }

    public static void main(String[] args) {
        // Création de l'arbre vide
        ABR arbre = null;

        // Ajout des noms propres avec leurs numéros de page
        arbre = new ABR("Fatou", new LCB().ajout_numero(110).ajout_numero(250).ajout_numero(300));
        arbre = arbre.ajout_nompropre("Mamadou", new int[] { 3, 14, 101 }, arbre);
        arbre = arbre.ajout_nompropre("Ousseynou", new int[] { 11, 50 }, arbre);
        arbre = arbre.ajout_nompropre("Pierre", new int[] { 3, 7, 100, 287 }, arbre);
        arbre = arbre.ajout_nompropre("Soda", new int[] { 6, 10, 34, 66, 98 }, arbre);

        // Affichage de l'index alphabétique
        System.out.println("Index alphabétique des noms propres : ");
        arbre.afficher_index(arbre);
    }
}
