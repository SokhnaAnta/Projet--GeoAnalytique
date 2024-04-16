// Exo 7 : Arbres
#include <stdio.h>
#include <stdlib.h>

struct Noeud
{
    int valeur;
    struct Noeud *gauche;
    struct Noeud *droit;
};

typedef struct Noeud *ARB_BIN;

void FEUILLE(ARB_BIN A)
{
    if (A != NULL)
    {
        if (A->gauche == NULL && A->droit == NULL)
        {
            printf("%d ", A->valeur); // Afficher l'étiquette si c'est une feuille
        }
        FEUILLE(A->gauche); // Parcourir le sous-arbre gauche
        FEUILLE(A->droit);  // Parcourir le sous-arbre droit
    }
}
void degre(ARB_BIN A)
{
    if (A != NULL)
    {
        int deg = 0;

        // Incrémenter le degré si le nœud a un fils gauche
        if (A->gauche != NULL)
        {
            deg++;
        }

        // Incrémenter le degré si le nœud a un fils droit
        if (A->droit != NULL)
        {
            deg++;
        }

        printf("Noeud : %d, Degre : %d\n", A->valeur, deg);

        // Parcourir le sous-arbre gauche
        degre(A->gauche);

        // Parcourir le sous-arbre droit
        degre(A->droit);
    }
}


int PROFONDEUR(ARB_BIN A, int x) {
    if (A == NULL) {
        return -1; // Arbre vide, donc x n'est pas trouvé
    }
    if (A->valeur == x) {
        return 0; // x est à la racine de l'arbre
    }
    int profondeur_gauche = PROFONDEUR(A->gauche, x);
    if (profondeur_gauche >= 0) {
        return 1 + profondeur_gauche; // x trouvé dans le sous-arbre gauche
    }
    int profondeur_droit = PROFONDEUR(A->droit, x);
    if (profondeur_droit >= 0) {
        return 1 + profondeur_droit; // x trouvé dans le sous-arbre droit
    }
    return -1; // x non trouvé dans cet arbre
}

int maximum(int a, int b) {
    return (a > b) ? a : b;
}


int HAUTEUR(ARB_BIN A)
{
    if (A == NULL)
    {
        return -1; // Hauteur d'un arbre vide
    }
    else
    {
        return 1 + maximum(HAUTEUR(A->gauche), HAUTEUR(A->droit));
    }
}

int SOM_NOEUDS(ARB_BIN A)
{
    if (A == NULL)
    {
        return 0; // Somme des nœuds d'un arbre vide
    }
    else
    {
        return A->valeur + SOM_NOEUDS(A->gauche) + SOM_NOEUDS(A->droit);
    }
}

int main()
{
    // Exemple d'utilisation
    // Création de l'arbre
    ARB_BIN racine = (ARB_BIN)malloc(sizeof(struct Noeud));
    racine->valeur = 1;
    racine->gauche = (ARB_BIN)malloc(sizeof(struct Noeud));
    racine->gauche->valeur = 2;
    racine->gauche->gauche = NULL;
    racine->gauche->droit = NULL;
    racine->droit = (ARB_BIN)malloc(sizeof(struct Noeud));
    racine->droit->valeur = 3;
    racine->droit->gauche = NULL;
    racine->droit->droit = NULL;

    // Appels des fonctions et procédures
    printf("Liste des feuilles: ");
    FEUILLE(racine);
    printf("\n");

    printf("Degres des noeuds:\n");
    degre(racine);

    int x = 3;
    printf("Profondeur de %d dans l'arbre: %d\n", x, PROFONDEUR(racine, x));

    printf("Hauteur de l'arbre: %d\n", HAUTEUR(racine));

    printf("Somme des noeuds de l'arbre: %d\n", SOM_NOEUDS(racine));

    // Libération de la mémoire
    free(racine->gauche);
    free(racine->droit);
    free(racine);

    return 0;
}