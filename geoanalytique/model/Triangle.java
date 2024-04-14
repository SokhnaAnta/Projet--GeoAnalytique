package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public  class  Triangle extends Polygone {
    private Point pointA;
    private Point pointB;
    private Point pointC;
    

 public Triangle(Point pointA, Point pointB, Point pointC,String nom) {
    super(nom) ;
    this.pointA = pointA;
    this.pointB = pointB;
    this.pointC = pointC;
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
public Point getPointC() {
    return pointC;
}

public void setPointC(Point pointC) {
    this.pointC = pointC;
}

public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
    return visitor.visit(this);
}
    
}
