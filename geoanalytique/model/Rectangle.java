package geoanalytique.model;


public class Rectangle extends Parallelogramme{

    public Rectangle(Point pointA, Point pointB, Point pointC, Point pointD,String nom) {
        super(pointA, pointB, pointC, pointD,nom);
    }
   // public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
    //    return visitor.visit(this);
   // }
    
}
