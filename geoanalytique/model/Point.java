package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public class Point extends GeoObject{
    private double abscisse;
    private double ordonnee;

    public Point(double abscisse, double ordonnee,String nom) {
        super(nom) ;
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    public double getAbscisse() {
        return abscisse;
    }

    public void setAbscisse(double abscisse) {
        this.abscisse = abscisse;
    }

    public double getOrdonnee() {
        return ordonnee;
    }

    public void setOrdonnee(double ordonnee) {
        this.ordonnee = ordonnee;
    }

    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
}
