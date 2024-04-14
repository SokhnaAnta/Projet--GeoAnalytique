package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public class Cercle extends Ellipse {

    public Cercle(Point centre, double rayon,String nom) {
        super(centre, rayon, rayon,nom); 
    }
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }

}


