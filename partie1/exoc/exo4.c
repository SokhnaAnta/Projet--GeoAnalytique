#include <stdio.h>
#include <stdlib.h>

typedef struct Maillon
{
    int valeur;
    int exposant;
    struct Maillon *suivant;
} maillon, *PTR;

// Fonction pour ajouter un terme à la liste chainee
void ajouter_terme(PTR *tete, int coefficient, int exposant)
{
    PTR terme = (PTR)malloc(sizeof(struct Maillon));
    terme->valeur = coefficient;
    terme->exposant = exposant;
    terme->suivant = *tete;
    *tete = terme;
}

// calcule la derivee du polynome P
PTR DERIVEE(PTR P)
{
    PTR derivee = NULL;
    PTR terme = P;

    while (terme != NULL)
    {
        // Calcul de la derivee du terme actuel
        int coefficient = terme->valeur;
        int exposant = terme->exposant;

        if (exposant > 0)
        {
            // Creation d'un nouveau maillon pour le terme derive
            PTR terme = (PTR)malloc(sizeof(struct Maillon));
            terme->valeur = coefficient * exposant;
            terme->exposant = exposant - 1;
            terme->suivant = NULL;

            // Ajout du nouveau terme à la liste de la derivee
            if (derivee == NULL)
            {
                derivee = terme;
            }
            else
            {
                PTR dernierTerme = derivee;
                while (dernierTerme->suivant != NULL)
                {
                    dernierTerme = dernierTerme->suivant;
                }
                dernierTerme->suivant = terme;
            }
        }

        terme = terme->suivant;
    }

    return derivee;
}

PTR DERIVEEKIEME(PTR P, int k)
{
    PTR derivee = P;
    int i;

    for (i = 0; i < k; i++)
    {
        derivee = DERIVEE(derivee);
    }

    return derivee;
}

// Fonction pour afficher un polynome
void afficher_polynome(PTR P)
{
    PTR terme = P;

    while (terme != NULL)
    {
        printf("+ %dx^%d ", terme->valeur, terme->exposant);
        terme = terme->suivant;
    }
    printf("\n");
}

void liberer_liste(PTR liste)
{
    PTR temp;
    while (liste != NULL)
    {
        temp = liste;
        liste = liste->suivant;
        free(temp);
    }
}

int main()
{
    PTR P = NULL;
    ajouter_terme(&P, 3, 7);  // Terme 3x^7
    ajouter_terme(&P, -2, 1); // Terme -2x
    ajouter_terme(&P, 1, 0);  // Terme 1 (constante)

    printf("Polynome initial : ");
    afficher_polynome(P);

    PTR derivee1 = DERIVEE(P);
    printf("Derivee 1ere : ");
    afficher_polynome(derivee1);

    PTR derivee3 = DERIVEEKIEME(P, 3);
    printf("troisieme derivee de P : ");
    afficher_polynome(derivee3);

    liberer_liste(P);
    liberer_liste(derivee1);
    liberer_liste(derivee3);
    return 0;
}
