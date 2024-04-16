package exojava.exo6;

public class MatriceCARRE {
    protected double[][] matrice;

    public MatriceCARRE(int n) {
        matrice = new double[n][n];
    }

    // Fonction pour vérifier si la matrice est symétrique
    public boolean symetrique() {
        int n = matrice.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (matrice[i][j] != matrice[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 3. Combien faut-il de nombres réels pour représenter sans redondance une
    // matrice symétrique à n lignes et n colonnes?
    // Reponse : n * (n + 1) / 2

    // Fonction pour construire et renvoyer la représentation compacte d'une matrice
    // symétrique

    public double[] symCompacte(int n) {
        if (!symetrique()) {
            System.out.println("La matrice n'est pas symétrique.");
            return null;
        }

        double[] compacte = new double[n * (n + 1) / 2];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                compacte[k++] = matrice[i][j]; // Stockage de la partie inférieure de la matrice symétrique
            }
        }
        return compacte;
    }

    // methode acces
    public static double acces(double[] c, int i, int j, int n) {
        // Vérifier si les indices i et j sont dans les limites valides
        if (i < 0 || j < 0 || i >= n || j >= n) {
            // Afficher un message d'erreur
            System.out.println("Indices invalides : (" + i + ", " + j + ")");
            return Double.NaN;
        }

        // Si i est inférieur à j, échanger les indices
        if (i < j) {
            int temp = i;
            i = j;
            j = temp;
        }

        // Calculer l'indice dans le tableau compacte
        int index = i * (i + 1) / 2 + j;

        return c[index];
    }

    // Fonction pour accéder au coefficient (i, j) dans la représentation compacte
    // Méthode pour traiter une ligne de la matrice symétrique à partir de sa
    // représentation compacte
    public static void traiterLigne(double[] c, int n, int i) {
        // Vérifier si l'indice de ligne est valide
        if (i < 0 || i >= n) {
            System.out.println("Indice de ligne invalide : " + i);
            return;
        }

        for (int j = 0; j <= i; j++) {
            // Traitement du coefficient c[i][j]
            // Ici, nous supposons l'existence d'une fonction traiterCoef(double x)
            // pour effectuer le traitement sur chaque coefficient
            traiterCoef(c[i * (i + 1) / 2 + j]);
        }
    }

    // Fonction pour afficher la matrice symétrique à partir de sa représentation
    // compacte
    public static void afficher(double[] c, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(acces(c, i, j, n) + " ");
            }
            System.out.println();
        }
    }

    // Méthode pour traiter chaque coefficient individuellement
    private static void traiterCoef(double x) {
        // Ajoutez ici le traitement spécifique à chaque coefficient
        System.out.print(" " + x);
    }

    public static void main(String[] args) {
        // Création d'une matrice carrée
        MatriceCARRE matrice = new MatriceCARRE(4);

        // Remplissage de la matrice avec des valeurs symétriques
        matrice.matrice[0][0] = 1;
        matrice.matrice[1][1] = 2;
        matrice.matrice[2][2] = 3;
        matrice.matrice[3][3] = 4;
        matrice.matrice[0][1] = 5;
        matrice.matrice[1][0] = 5;
        matrice.matrice[0][2] = 6;
        matrice.matrice[2][0] = 6;
        matrice.matrice[0][3] = 7;
        matrice.matrice[3][0] = 7;
        matrice.matrice[1][2] = 8;
        matrice.matrice[2][1] = 8;
        matrice.matrice[1][3] = 9;
        matrice.matrice[3][1] = 9;
        matrice.matrice[2][3] = 10;
        matrice.matrice[3][2] = 10;

        // Vérification si la matrice est symétrique
        System.out.println("La matrice est symétrique : " + matrice.symetrique());

        // Récupération de la représentation compacte
        double[] compacte = matrice.symCompacte(4);

        // Affichage de la matrice symétrique à partir de sa représentation compacte
        System.out.println("Matrice symétrique à partir de sa representation compacte: ");
        afficher(compacte, 4);

        // Accès à un coefficient de la matrice symétrique
        int i = 2;
        int j = 1;
        double coefficient = acces(compacte, i, j, 4);
        System.out.println("Coefficient à la position (" + i + ", " + j + ") : " + coefficient);

        // Traitement de la ligne de la matrice symétrique
        int ligne = 2;
        System.out.println("Traitement de la ligne :" + ligne);

        traiterLigne(compacte, 4, ligne);
    }

}
