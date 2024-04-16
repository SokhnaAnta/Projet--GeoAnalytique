package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;
/**
 * Classe abstraite représentant un objet géométrique dans l'application GeoAnalytique.
 * Elle sert de base pour tous les types d'objets géométriques qui peuvent être créés et manipulés.
 */

public abstract class GeoObject {
    // Nom de l'objet géométrique, utilisé pour l'identification dans l'interface utilisateur.
    String nom;


     /**
     * Constructeur de GeoObject.
     * @param nom Le nom de l'objet géométrique.
     */

     
    public GeoObject(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode abstraite pour accepter un visiteur dans le cadre du design pattern visiteur.
     * Cette méthode permet de séparer les algorithmes des objets sur lesquels ils opèrent.
     * @param visitor Le visiteur qui effectue des opérations sur l'objet.
     * @return Un objet de type {@link Graphique}, qui est une représentation graphique de cet objet géométrique.
     */
    public abstract <T extends Graphique> T accept(GeoObjectVisitor<T> visitor);
}
