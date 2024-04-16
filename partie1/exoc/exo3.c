#include <stdio.h>
#include <stdlib.h>

typedef struct Maillon
{
    int valeur;
    struct Maillon *suivant;
} Maillon, *PTR;

// Procedure pour creer une liste avec dix valeurs saisies au clavier
PTR creer_liste()
{
    PTR tete = NULL;
    PTR dernier = NULL;

    printf("Saisissez dix entiers pour creer la liste :\n");
    for (int i = 0; i < 10; i++)
    {
        PTR nouveauMaillon = (PTR)malloc(sizeof(struct Maillon));
        if (nouveauMaillon == NULL)
        {
            printf("Echec \n");
            return NULL;
        }

        printf("Entier %d : ", i + 1);
        scanf("%d", &(nouveauMaillon->valeur));
        nouveauMaillon->suivant = NULL;

        if (tete == NULL)
        {
            tete = dernier = nouveauMaillon;
        }
        else
        {
            dernier->suivant = nouveauMaillon;
            dernier = dernier->suivant;
        }
    }
    return tete;
}

// Fonction pour tester l'egalite de deux listes
int comparer_listes(PTR liste1, PTR liste2)
{
    while (liste1 != NULL && liste2 != NULL)
    {
        if (liste1->valeur != liste2->valeur)
        {
            return 0; // Les listes sont differentes
        }
        liste1 = liste1->suivant;
        liste2 = liste2->suivant;
    }
    // Les deux listes ont la meme longueur et les memes valeurs
    return (liste1 == NULL && liste2 == NULL);
}

// Fonction pour afficher une liste
void afficher_liste(PTR l)
{
    // PTR liste = L ;
    printf("Liste : ");
    while (l != NULL)
    {
        printf("%d ", l->valeur);
        l = l->suivant;
    }
    printf("\n");
}
PTR concatener_listes(PTR liste1, PTR liste2)
{
    PTR nouvelleListe = NULL;
    PTR courant = NULL;

    // Copie de la premiere liste
    while (liste1 != NULL)
    {
        PTR nouveauMaillon = (PTR)malloc(sizeof(Maillon));
        if (nouveauMaillon == NULL)
        {
            printf("Echec \n");
            return NULL;
        }
        nouveauMaillon->valeur = liste1->valeur;
        nouveauMaillon->suivant = NULL;

        if (nouvelleListe == NULL)
        {
            nouvelleListe = nouveauMaillon;
            courant = nouvelleListe;
        }
        else
        {
            courant->suivant = nouveauMaillon;
            courant = courant->suivant;
        }

        liste1 = liste1->suivant;
    }

    // Concatenation de la deuxieme liste
    while (liste2 != NULL)
    {
        PTR nouveauMaillon = (PTR)malloc(sizeof(Maillon));
        nouveauMaillon->valeur = liste2->valeur;
        nouveauMaillon->suivant = NULL;
        if (nouvelleListe == NULL)
        {
            nouvelleListe = nouveauMaillon;
            courant = nouvelleListe;
        }
        else
        {
            courant->suivant = nouveauMaillon;
            courant = courant->suivant;
        }

        liste2 = liste2->suivant;
    }

    return nouvelleListe;
}

// Fonction pour concatener deux listes sans creer une troisieme liste
void concatener_listes_sans_troisieme(PTR liste1, PTR liste2)
{
    if (liste1 == NULL)
    {
        liste1 = liste2;
        return;
    }

    while (liste1->suivant != NULL)
    {
        liste1 = liste1->suivant;
    }

    liste1->suivant = liste2;
}

// Fonction pour liberer la memoire allouee pour une liste
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
    // Creation des deux listes
    printf("Creation de la premiere liste :\n");
    PTR liste1 = creer_liste();

    printf("Creation de la deuxieme liste :\n");
    PTR liste2 = creer_liste();

    // egalite des listes
    if (comparer_listes(liste1, liste2))
    {
        printf("Les deux listes sont egales.\n");
    }
    else
    {
        printf("Les deux listes sont differentes.\n");
    }

    PTR liste3 = concatener_listes(liste1, liste2);
    printf("Concatenation des deux listes sans creer une troisieme liste :\n");
    afficher_liste(liste3);

    concatener_listes_sans_troisieme(liste1, liste2);
    printf("Concatenation des deux listes sans creer une troisieme liste :\n");
    // liste1 qui contient maintenant les elements des deux listes
    afficher_liste(liste1);

    // Liberation de la memoire allouee pour les listes
    liberer_liste(liste1);
    liberer_liste(liste2);
    liberer_liste(liste3);

    return 0;
}
