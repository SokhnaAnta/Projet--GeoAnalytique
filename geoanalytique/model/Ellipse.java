package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

/**
 * La classe Ellipse représente une ellipse dans un espace géométrique.
 */
public class Ellipse extends Surface {
    private Point centre;
    private double rayonX;
    private double rayonY;

    /**
     * Constructeur de la classe Ellipse.
     * @param centre Le centre de l'ellipse.
     * @param rayonX Le rayon horizontal de l'ellipse.
     * @param rayonY Le rayon vertical de l'ellipse.
     * @param nom Le nom de l'ellipse.
     */
    public Ellipse(Point centre, double rayonX, double rayonY, String nom) {
        // Appelle le constructeur de la classe mère (Surface) avec le nom donné.
        super(nom);
        this.centre = centre;
        this.rayonX = rayonX;
        this.rayonY = rayonY;
    }

    /**
     * Obtient le centre de l'ellipse.
     * @return Le centre de l'ellipse.
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Définit le centre de l'ellipse.
     * @param centre Le nouveau centre de l'ellipse.
     */
    public void setCentre(Point centre) {
        this.centre = centre;
    }

    /**
     * Obtient le rayon horizontal de l'ellipse.
     * @return Le rayon horizontal de l'ellipse.
     */
    public double getRayonX() {
        return rayonX;
    }

    /**
     * Définit le rayon horizontal de l'ellipse.
     * @param rayonX Le nouveau rayon horizontal de l'ellipse.
     */
    public void setRayonX(double rayonX) {
        this.rayonX = rayonX;
    }

    /**
     * Obtient le rayon vertical de l'ellipse.
     * @return Le rayon vertical de l'ellipse.
     */
    public double getRayonY() {
        return rayonY;
    }

    /**
     * Définit le rayon vertical de l'ellipse.
     * @param rayonY Le nouveau rayon vertical de l'ellipse.
     */
    public void setRayonY(double rayonY) {
        this.rayonY = rayonY;
    }

    /**
     * Méthode permettant à un visiteur de traiter cette ellipse.
     * @param visitor Le visiteur à appliquer.
     * @param <T> Le type de graphique retourné par le visiteur.
     * @return Le résultat de la visite.
     */
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
