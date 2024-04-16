#include <stdio.h>
#include <stdlib.h>

typedef struct Maillon {
    int indice;
    float valeur;
    struct Maillon *suivant;
} Maillon, *PTR;

PTR nouveau_maillon(int indice, float valeur) {
    PTR nouveau = (PTR)malloc(sizeof(Maillon));
    if (nouveau == NULL) {
        printf("Erreur\n");
        return NULL ;
    }
    nouveau->indice = indice;
    nouveau->valeur = valeur;
    nouveau->suivant = NULL;
    return nouveau;
}

PTR vecteur_creux(float t[], int n) {
    PTR tete = NULL;
    PTR dernier = NULL;
    
    int i;
    for (i = 0; i < n; i++) {
        if (t[i] != 0) {
            PTR nouveau = nouveau_maillon(i, t[i]);
            if (tete == NULL) {
                tete = nouveau;
                dernier = nouveau;
            } else {
                dernier->suivant = nouveau;
                dernier = dernier->suivant;
            }
        }
    }
    return tete;
}

//fonction pour calculer la somme de deux vecteurs creux
PTR somme(PTR a, PTR b) {
    PTR resultat = NULL; //tete
    PTR dernier = NULL;

    //tant que l'un des vecteurs n'est pas entièrement parcouru
    while (a != NULL || b != NULL) { 
        if (a == NULL || (b != NULL && a->indice > b->indice)) {
            //ajouter le maillon de b
            PTR nouveau = nouveau_maillon(b->indice, b->valeur);
            if (resultat == NULL) {
                //first donc resultat et dernier pointe vers le meme object mais pour ne pas perdre la tete on garde resultat comme tete 
                resultat = nouveau;
                dernier = nouveau;
            } else {
                dernier->suivant = nouveau;
                dernier = dernier->suivant;
            }
            b = b->suivant;
        } else if (b == NULL || (a != NULL && a->indice < b->indice)) {
            //Ajouter le maillon de a
            PTR nouveau = nouveau_maillon(a->indice, a->valeur);
            if (resultat == NULL) {
                resultat = nouveau;
                dernier = nouveau;
            } else {
                dernier->suivant = nouveau;
                dernier = dernier->suivant;
            }
            a = a->suivant;
        } else {
            //same indice
            //Ajouter la somme des maillons a et b
            float somme = a->valeur + b->valeur;
            if (somme != 0) {
                PTR nouveau = nouveau_maillon(a->indice, somme);
                if (resultat == NULL) {
                    resultat = nouveau;
                    dernier = nouveau;
                } else {
                    dernier->suivant = nouveau;
                    dernier = dernier->suivant;
                }
            }
            a = a->suivant;
            b = b->suivant;
        }
    }

    return resultat;
    
}


//fonction pour afficher un vecteur creux
void afficher_vecteur_creux(PTR vecteur) {
    printf("[");
    while (vecteur != NULL) {
        printf("(%d, %f)", vecteur->indice, vecteur->valeur);
        if (vecteur->suivant != NULL)
            printf(", ");
        vecteur = vecteur->suivant;
    }
    printf("]\n");
}

void liberer_liste(PTR liste) {
    PTR temp;
    while (liste != NULL) {
        temp = liste;
        liste = liste->suivant;
        free(temp);
    }
}

int main() {
    float tableau1[] = {0, -2, 9, 5, 0, 7, 0, 3, 0, 0};
    float tableau2[] = {0, 2, 0, 0, 0, 3, 0, 4, 0, 1};

    PTR vecteur1 = vecteur_creux(tableau1, 10);
    PTR vecteur2 = vecteur_creux(tableau2, 10);

    printf("vecteur 1 : ");
    afficher_vecteur_creux(vecteur1);
    printf("vecteur 2 : ");
    afficher_vecteur_creux(vecteur2);

    PTR sommevecteur = somme(vecteur1, vecteur2);
    printf("somme des vecteurs : ");
    afficher_vecteur_creux(sommevecteur);

   
   
    liberer_liste(vecteur1);
    liberer_liste(vecteur2);
    liberer_liste(sommevecteur);


    return 0;
}
