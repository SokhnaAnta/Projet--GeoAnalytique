package geoanalytique.model ;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public class Droite extends GeoObject {
    private Point pointA;
    private Point pointB;
   

   
    public Droite(Point pointA, Point pointB,String nom) {
        super(nom);
        this.pointA = pointA;
        this.pointB = pointB;
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
    
    public void setPoints(Point nouveauPointA, Point nouveauPointB) {
        this.pointA = nouveauPointA ;
        this.pointB = nouveauPointB ;
    }

    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
}