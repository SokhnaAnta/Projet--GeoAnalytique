package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

public class Ellipse extends Surface {
    private Point centre;
    private double rayonX;
    private double rayonY;

    // Constructeur
    public Ellipse(Point centre, double rayonX, double rayonY,String nom) {
        super(nom) ;
        this.centre = centre;
        this.rayonX = rayonX;
        this.rayonY = rayonY;
    }


    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public double getRayonX() {
        return rayonX;
    }

    public void setRayonX(double rayonX) {
        this.rayonX = rayonX;
    }

    public double getRayonY() {
        return rayonY;
    }

    public void setRayonY(double rayonY) {
        this.rayonY = rayonY;
    }
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
