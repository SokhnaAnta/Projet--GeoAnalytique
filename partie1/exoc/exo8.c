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
    struct noeudABR* gauche;
    struct noeudABR* droite;
} ABR;

unsigned long hash_function(char* str) {
    unsigned long hash = 0;
    int c;

    while ((c = *str++)) {
        hash = c + (hash << 6) + (hash << 16) - hash;
    }

    return hash % TABLE_SIZE;
}

ABR* ajout_numero(int num, ABR* a) {
    PTR nouveau_maillon = (PTR)malloc(sizeof(MAILLON));
    nouveau_maillon->numero = num;
    nouveau_maillon->suiv = NULL;
    nouveau_maillon->prec = a->numeros.queue;

    if (a->numeros.tete == NULL) {
        a->numeros.tete = nouveau_maillon;
        a->numeros.queue = nouveau_maillon;
    } else {
        a->numeros.queue->suiv = nouveau_maillon;
        a->numeros.queue = nouveau_maillon;
    }

    return a;
}

ABR* ajout_nompropre(char* nom, int t[], int nombre, ABR* table[]) {
    unsigned long hash = hash_function(nom);

    ABR* nouvel_element = (ABR*)malloc(sizeof(ABR));
    nouvel_element->nom = strdup(nom);
    nouvel_element->gauche = NULL;
    nouvel_element->droite = NULL;
    nouvel_element->numeros.tete = NULL;
    nouvel_element->numeros.queue = NULL;

    for (int i = 0; i < nombre; i++) {
        nouvel_element = ajout_numero(t[i], nouvel_element);
    }

    if (table[hash] == NULL) {
        table[hash] = nouvel_element;
    } else {
        ABR* current = table[hash];
        while (current->droite != NULL) {
            current = current->droite;
        }
        current->droite = nouvel_element;
    }

    return table[hash];
}

ABR* supprimer_numero(char* nom, int numero, ABR* a) {
    if (a == NULL) {
        return NULL;
    }

    int cmp = strcmp(nom, a->nom);
    if (cmp < 0) {
        a->gauche = supprimer_numero(nom, numero, a->gauche);
    } else if (cmp > 0) {
        a->droite = supprimer_numero(nom, numero, a->droite);
    } else {
        PTR current = a->numeros.tete;
        while (current != NULL) {
            if (current->numero == numero) {
                if (current == a->numeros.tete) {
                    a->numeros.tete = current->suiv;
                    if (a->numeros.tete != NULL) {
                        a->numeros.tete->prec = NULL;
                    }
                } else if (current == a->numeros.queue) {
                    a->numeros.queue = current->prec;
                    if (a->numeros.queue != NULL) {
                        a->numeros.queue->suiv = NULL;
                    }
                } else {
                    current->prec->suiv = current->suiv;
                    current->suiv->prec = current->prec;
                }
                free(current);
                break;
            }
            current = current->suiv;
        }
    }

    return a;
}

void afficher_index(ABR* a) {
    if (a != NULL) {
        afficher_index(a->gauche);
        printf("%s: ", a->nom);
        PTR current = a->numeros.tete;
        while (current != NULL) {
            printf("%d ", current->numero);
            current = current->suiv;
        }
        printf("\n");
        afficher_index(a->droite);
    }
}

int main() {
    ABR* table[TABLE_SIZE] = { NULL };

    int nums_fatou[] = { 110, 250, 300 };
    int nums_mamadou[] = { 3, 14, 101 };
    int nums_ousseynou[] = { 11, 50 };
    int nums_pierre[] = { 3, 7, 100, 287 };
    int nums_soda[] = { 6, 10, 34, 66, 98 };

    ajout_nompropre("Fatou", nums_fatou, 3, table);
    ajout_nompropre("Mamadou", nums_mamadou, 3, table);
    ajout_nompropre("Ousseynou", nums_ousseynou, 2, table);
    ajout_nompropre("Pierre", nums_pierre, 4, table);
    ajout_nompropre("Soda", nums_soda, 5, table);

    printf("Index alphabétique:\n");
    for (int i = 0; i < TABLE_SIZE; i++) {
        afficher_index(table[i]);
    }

    // Supprimer un numéro (par exemple, supprimer le numéro 250 pour Fatou)
    printf("\nSupprimer le numéro 250 pour Fatou :\n");
    table[hash_function("Fatou")] = supprimer_numero("Fatou", 250, table[hash_function("Fatou")]);
    afficher_index(table[hash_function("Fatou")]);

    return 0;
}
