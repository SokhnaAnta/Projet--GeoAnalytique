package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public class Carre extends Parallelogramme{

    public Carre(Point pointA, Point pointB, Point pointC, Point pointD,String nom) {
        super(pointA, pointB, pointC, pointD, nom);
    }
    
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
