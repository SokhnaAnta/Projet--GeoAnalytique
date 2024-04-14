package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public class Losange extends Parallelogramme {

    public Losange(Point pointA, Point pointB, Point pointC, Point pointD,String nom) {
        super(pointA, pointB, pointC, pointD, nom);
    }
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
}
