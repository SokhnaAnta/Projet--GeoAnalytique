#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure d'un maillon de la liste
typedef struct maillon
{
    char *mot;
    struct maillon *suiv;
} MAILLON, *PTR;

// Fonction pour ajouter un mot en tête de la liste
PTR ajoute_debut(char *mot, PTR L)
{
    PTR nouveau_maillon = (PTR)malloc(sizeof(MAILLON));
    if (nouveau_maillon == NULL)
    {
        printf("Erreur d'allocation de memoire.\n");
        exit(EXIT_FAILURE);
    }
    nouveau_maillon->mot = strdup(mot); // Duplique le mot pour eviter des problèmes potentiels
    nouveau_maillon->suiv = L;
    return nouveau_maillon;
}

// Fonction pour ajouter un mot en fin de la liste
PTR ajoute_fin(char *mot, PTR L)
{
    PTR nouveau_maillon = (PTR)malloc(sizeof(MAILLON));
    if (nouveau_maillon == NULL)
    {
        printf("Erreur d'allocation de memoire.\n");
        exit(EXIT_FAILURE);
    }
    nouveau_maillon->mot = strdup(mot); // Duplique le mot pour eviter des problèmes potentiels
    nouveau_maillon->suiv = NULL;

    if (L == NULL)
    {
        return nouveau_maillon;
    }

    PTR courant = L;
    while (courant->suiv != NULL)
    {
        courant = courant->suiv;
    }
    courant->suiv = nouveau_maillon;

    return L;
}

// Fonction pour supprimer le premier mot de la liste
PTR supprimer(char *mot, PTR L)
{
    PTR courant = L;
    PTR precedent = NULL;

    while (courant != NULL)
    {
        if (strcmp(courant->mot, mot) == 0)
        {
            if (precedent == NULL) // le premier
            {
                L = courant->suiv;
            }
            else
            {
                precedent->suiv = courant->suiv;
            }
            free(courant->mot);
            free(courant);
            break;
        }
        precedent = courant;
        courant = courant->suiv;
    }

    return L;
}

// supprimer tout les mots contenant la valeur
PTR supprimer_tout(char *mot, PTR L)
{
    PTR courant = L;
    PTR precedent = NULL;
    while (courant != NULL)
    {
        if (strcmp(courant->mot, mot) == 0)
        {
            if (precedent == NULL)
            {
                L = courant->suiv;
            }
            else
            {
                precedent->suiv = courant->suiv;
            }
            free(courant->mot);
            free(courant);
        }
        precedent = courant;
        courant = courant->suiv;
    }
    return L;
}

// Fonction pour afficher les n premiers mots de la liste
void premiers(PTR liste, int n)
{
    int compteur = 0;
    PTR courant = liste;

    while (courant != NULL && compteur < n)
    {
        printf("%s\t", courant->mot);
        courant = courant->suiv;
        compteur++;
    }
    printf("\n");
}

// Fonction pour trier la liste
PTR sort(PTR tete)
{
    if (tete == NULL || tete->suiv == NULL)
    {
        return tete; // La liste est vide ou a un seul element, pas besoin de trier
    }

    int fait;
    PTR courant;
    PTR dernier = NULL;

    do
    {
        fait = 0;
        courant = tete;

        while (courant->suiv != dernier) //!= NULL
        {
            if (strcmp(courant->mot, courant->suiv->mot) > 0)
            {
                char *temp = courant->mot;
                courant->mot = courant->suiv->mot;
                courant->suiv->mot = temp;
                fait = 1;
            }
            courant = courant->suiv;
        }
        dernier = courant;
    } while (fait);

    return tete;
}

// Fonction pour supprimer les doublons dans une liste triee
PTR purifie(PTR liste)
{
    if (liste == NULL)
    {
        return NULL;
    }

    PTR courant = liste;
    while (courant->suiv != NULL)
    {
        // Comparer le mot actuel avec le mot suivant
        if (strcmp(courant->mot, courant->suiv->mot) == 0)
        {
            // Le mot suivant est un doublon, supprimons-le
            PTR maillon_a_supprimer = courant->suiv;
            courant->suiv = courant->suiv->suiv; // Mettre à jour le lien
            free(maillon_a_supprimer->mot);      // Liberer la memoire du mot
            free(maillon_a_supprimer);           // Liberer la memoire du maillon
        }
        else
        {
            // Passer au maillon suivant
            courant = courant->suiv;
        }
    }

    return liste;
}

// Fonction pour afficher les mots de la liste
void lireList(PTR liste)
{
    PTR temp = liste;
    while (temp != NULL)
    { // liste aussi marche car on a pas acces à l'adresse mais à la valeur
        printf("%s \t ", temp->mot);
        temp = temp->suiv;
    }
    printf("\n");
}

// Fonction pour liberer la memoire de la liste
void liberer_liste(PTR L)
{
    while (L != NULL)
    {
        PTR temp = L;
        L = L->suiv;
        free(temp->mot);
        free(temp);
    }
}

int main()
{
    PTR liste;
    printf("Ajout au debut :\n");
    liste = ajoute_debut("chat", liste);
    liste = ajoute_debut("chien", liste);
    liste = ajoute_debut("oiseau", liste);
    lireList(liste);

    printf("\nAjout à la fin :\n");

    liste = ajoute_fin("boeuf", liste);
    liste = ajoute_fin("souris", liste);
    liste = ajoute_fin("serpent", liste);
    liste = ajoute_fin("chien", liste);
    lireList(liste);

    printf("\nSupprimer chien :\n");
    liste = supprimer("chien", liste);
    lireList(liste);
    printf("\nSupprimer tout les valeurs chien :\n");
    liste = supprimer_tout("chien", liste);
    lireList(liste);

    printf("\n Lire les 2 premiers :\n");

    premiers(liste, 2);

    sort(liste);
    printf("\n Apres trie et avant purification :\n");
    lireList(liste);

    liste = purifie(liste);

    printf("\n Apres purification :\n");
    lireList(liste);

    liberer_liste(liste);
    return 0;
}
