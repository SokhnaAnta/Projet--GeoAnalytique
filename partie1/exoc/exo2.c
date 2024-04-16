#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct maillon {
    char *info; 
    struct maillon *suivant; 
    struct maillon *precedent; 
} MAILLON, *PTR; 

PTR premier = NULL;
PTR dernier = NULL;

void ajouter_devant(char *s) {
    PTR nouveau_maillon = (PTR)malloc(sizeof(MAILLON));
    if (nouveau_maillon == NULL) {
        printf("erreur\n");
        return ;
    }
    nouveau_maillon->info = s;
    nouveau_maillon->suivant = premier;
    nouveau_maillon->precedent = NULL;

    if (premier != NULL) {

        premier->precedent = nouveau_maillon;
    } else {
        dernier = nouveau_maillon;
    }

    premier = nouveau_maillon; //c'est juste pour dire que le nouveau est la tete now


}


 /*
 //v2
void ajouter_devant(PTR *premier, PTR *dernier, char *s) {
    PTR nouveau_maillon = (PTR)malloc(sizeof(MAILLON));
    if (nouveau_maillon == NULL) {
        printf("erreur\n");
        return ;
    }
    nouveau_maillon->info = s;
    nouveau_maillon->suivant = *premier;
    nouveau_maillon->precedent = NULL;

    if (*premier != NULL) {
        (*premier)->precedent = nouveau_maillon;
    } else {
        //Si la liste est vide, le nouveau maillon est egalement le dernier
        *dernier = nouveau_maillon;
    }

    *premier = nouveau_maillon;
}

*/




//Fonction pour supprimer un maillon de la liste
void supprimer(char *s) {
    PTR courant = premier;

    //Parcourir la liste jusqu'à trouver le maillon à supprimer
    while (courant != NULL) {
        if (strcmp(courant->info, s) == 0) {
            //Mettre à jour les pointeurs suivant et precedent des maillons voisins
            if (courant->precedent != NULL) {
                courant->precedent->suivant = courant->suivant;
            } else {
                //je suis le premier
                premier = courant->suivant;
                
            }
            if (courant->suivant != NULL) {
                courant->suivant->precedent = courant->precedent;
            } else {
                dernier = courant->precedent;
            }
                        
            free(courant);
            
            return; 
        }
        courant = courant->suivant;
    }
    printf("Aucun maillon avec l'information n'a ete trouve.\n", s);
}

void liberer_liste(PTR liste) {
    while (liste != NULL) {
        PTR maillon_suivant = liste->suivant;
        free(liste);
        
        liste = maillon_suivant;
    }
}


void lireList() {
    PTR liste = premier;
    while (liste != NULL) {
        printf("%s\t", liste->info);
        liste = liste->suivant;
    }

   liberer_liste(liste) ;
}



int main() {
    
   /* //v2 
    PTR premier = NULL ;
    PTR dernier = NULL ;
    ajouter_devant(&premier,&dernier,"Nouveau maillon en tete");
    ajouter_devant(&premier,&dernier,"Deuxieme maillon en tete");

    //Afficher les informations des maillons
    lireList(premier) ;
    
    */
    ajouter_devant("Premier");
    ajouter_devant("Deuxieme");
    ajouter_devant("Troisieme");
    
    printf("Affichage de la liste : \n");
    lireList();
    printf("\nSuppression: \n ") ;
    //supprimer("Deuxieme maillon en tete") ;
    //supprimer("bbb") ;
    supprimer("Troisieme");
    lireList() ;
   
   
    liberer_liste(premier) ;
    return 0;
}
