package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public abstract class GeoObject {

    String nom;

    public GeoObject(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public abstract <T extends Graphique> T accept(GeoObjectVisitor<T> visitor);
}
