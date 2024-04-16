#include <stdio.h>
#include <stdlib.h>

#define NMAX 100

// Définition d'une structure pour représenter une matrice carrée
typedef struct {
    double matrice[NMAX][NMAX];
    int n; // Taille de la matrice carrée
} MatriceCARREE;

// Fonction pour vérifier si la matrice est symétrique
int symetrique(MatriceCARREE m) {
    for (int i = 0; i < m.n; i++) {
        for (int j = 0; j < i; j++) {
            if (m.matrice[i][j] != m.matrice[j][i]) {
                return 0; // La matrice n'est pas symétrique
            }
        }
    }
    return 1; // La matrice est symétrique
}

// Fonction pour construire et renvoyer la représentation compacte d'une matrice symétrique
double *symCompacte(MatriceCARREE m) {
    if (!symetrique(m)) {
        printf("La matrice n'est pas symétrique.\n");
        return NULL;
    }

    int tailleCompacte = m.n * (m.n + 1) / 2;
    double *compacte = (double *)malloc(tailleCompacte * sizeof(double));
    if (compacte == NULL) {
        printf("Erreur lors de l'allocation mémoire.\n");
        exit(EXIT_FAILURE);
    }

    int k = 0;
    for (int i = 0; i < m.n; i++) {
        for (int j = 0; j <= i; j++) {
            compacte[k++] = m.matrice[i][j]; // Stockage de la partie inférieure de la matrice symétrique
        }
    }
    return compacte;
}

// Fonction pour accéder au coefficient (i, j) dans la représentation compacte
double acces(double *c, int i, int j, int n) {
    // Vérifier si les indices i et j sont dans les limites valides
    if (i < 0 || j < 0 || i >= n || j >= n) {
        // Afficher un message d'erreur
        printf("Indices invalides : (%d, %d)\n", i, j);
        return -1;
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

// Fonction pour traiter chaque coefficient individuellement
void traiterCoef(double x) {
    // Ajoutez ici le traitement spécifique à chaque coefficient
    printf(" %.2f", x);
}

// Méthode pour traiter une ligne de la matrice symétrique à partir de sa représentation compacte
void traiterLigne(double *c, int n, int i) {
    // Vérifier si l'indice de ligne est valide
    if (i < 0 || i >= n) {
        printf("Indice de ligne invalide : %d\n", i);
        return;
    }

    for (int j = 0; j <= i; j++) {
        // Traitement du coefficient c[i][j]
        // Ici, nous supposons l'existence d'une fonction traiterCoef(double x)
        // pour effectuer le traitement sur chaque coefficient
        traiterCoef(c[i * (i + 1) / 2 + j]);
    }
}

// Fonction pour afficher la matrice symétrique à partir de sa représentation compacte
void afficher(double *c, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            printf(" %.2f", acces(c, i, j, n));
        }
        printf("\n");
    }
}

int main() {
    // Création d'une matrice carrée
    MatriceCARREE matrice;
    matrice.n = 4;

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
    printf("La matrice est symétrique : %d\n", symetrique(matrice));

    // Récupération de la représentation compacte
    double *compacte = symCompacte(matrice);

    // Affichage de la matrice symétrique à partir de sa représentation compacte
    printf("Matrice symétrique à partir de sa representation compacte:\n");
    afficher(compacte, 4);

    // Accès à un coefficient de la matrice symétrique
    int i = 2;
    int j = 1;
    double coefficient = acces(compacte, i, j, 4);
    printf("Coefficient à la position (%d, %d) : %.2f\n", i, j, coefficient);

    // Traitement de la ligne de la matrice symétrique
    int ligne = 2;
    printf("Traitement de la ligne : %d\n", ligne);
    traiterLigne(compacte, 4, ligne);

    // Libération de la mémoire allouée dynamiquement
    free(compacte);

    return 0;
}
