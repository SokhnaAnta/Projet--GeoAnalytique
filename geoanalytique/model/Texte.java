package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public class Texte extends GeoObject{
    double x;
    double y ;
    String contenu;
    public Texte(double x, double y , String contenu, String nom) {
        super(nom);
        this.x = x;
        this.y = y ;
        this.contenu = contenu ; 
       
    }

    

    @Override
   public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
    return visitor.visit(this);
   }



    public double getX() {
        return x;
    }



    public void setX(double x) {
        this.x = x;
    }



    public double getY() {
        return y;
    }



    public void setY(double y) {
        this.y = y;
    }



    public String getContenu() {
        return contenu;
    }



    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
   
    
}
