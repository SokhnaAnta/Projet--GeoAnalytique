//Exo 8 : ABR
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_SIZE 1000

typedef struct maillon {
    int numero;
    struct maillon* suiv, *prec;
} MAILLON, *PTR;

typedef struct lcb {
    PTR tete, queue;
} LCB;

typedef struct noeudABR {
    char* nom;
    LCB numeros;
    struct noeudABR* suiv;
} ABR;

unsigned long hash_function(char* str) {
    unsigned long hash = 5381;
    int c;

    while ((c = *str++))
        hash = ((hash << 5) + hash) + c;

    return hash % TABLE_SIZE;
}

ABR* ajout_nompropre(char* nom, int t[], int nombre, ABR* table[]) {
    unsigned long hash = hash_function(nom);
    
    ABR* nouvel_element = (ABR*)malloc(sizeof(ABR));
    nouvel_element->nom = strdup(nom);
    nouvel_element->numeros.tete = NULL;
    nouvel_element->numeros.queue = NULL;
    nouvel_element->suiv = table[hash];
    table[hash] = nouvel_element;
    
    for (int i = 0; i < nombre; i++) {
        PTR nouveau_maillon = (PTR)malloc(sizeof(MAILLON));
        nouveau_maillon->numero = t[i];
        nouveau_maillon->suiv = NULL;
        nouveau_maillon->prec = NULL;
        
        if (nouvel_element->numeros.tete == NULL) {
            nouvel_element->numeros.tete = nouveau_maillon;
            nouvel_element->numeros.queue = nouveau_maillon;
        } else {
            nouvel_element->numeros.queue->suiv = nouveau_maillon;
            nouveau_maillon->prec = nouvel_element->numeros.queue;
            nouvel_element->numeros.queue = nouveau_maillon;
        }
    }
    
    return table[hash];
}

ABR* rechercher_nompropre(char* nom, ABR* table[]) {
    unsigned long hash = hash_function(nom);
    
    ABR* current = table[hash];
    while (current != NULL) {
        if (strcmp(current->nom, nom) == 0) {
            return current;
        }
        current = current->suiv;
    }
    
    return NULL;
}

void afficher_index(ABR* table[]) {
    for (int i = 0; i < TABLE_SIZE; i++) {
        ABR* current = table[i];
        while (current != NULL) {
            printf("%s: ", current->nom);
            PTR maillon = current->numeros.tete;
            while (maillon != NULL) {
                printf("%d ", maillon->numero);
                maillon = maillon->suiv;
            }
            printf("\n");
            current = current->suiv;
        }
    }
}

int main() {
    ABR* table[TABLE_SIZE] = {NULL};
    
    int nums_fatou[] = {110, 250, 300};
    int nums_mamadou[] = {3, 14, 101};
    int nums_ousseynou[] = {11, 50};
    int nums_pierre[] = {3, 7, 100, 287};
    int nums_soda[] = {6, 10, 34, 66, 98};
    
    table[hash_function("Fatou")] = ajout_nompropre("Fatou", nums_fatou, 3, table);
    table[hash_function("Mamadou")] = ajout_nompropre("Mamadou", nums_mamadou, 3, table);
    table[hash_function("Ousseynou")] = ajout_nompropre("Ousseynou", nums_ousseynou, 2, table);
    table[hash_function("Pierre")] = ajout_nompropre("Pierre", nums_pierre, 4, table);
    table[hash_function("Soda")] = ajout_nompropre("Soda", nums_soda, 5, table);
    
    printf("Index alphabétique:\n");
    afficher_index(table);
    
    return 0;
}
 