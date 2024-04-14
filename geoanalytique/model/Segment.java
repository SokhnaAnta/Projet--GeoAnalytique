package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public class Segment extends Droite {
    private Point pointA;
    private Point pointB;

    public Segment(Point pointA, Point pointB,double longueur,String nom) {
        super(pointA,pointB,longueur,nom);
        
    }

    public Segment(Point pointA, Point pointB,String nom) {
        super(pointA,pointB,nom);
    }
   

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

  
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
