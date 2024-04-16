package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

/**
 * La classe Triangle représente un triangle dans un espace géométrique.
 * Un triangle est un polygone à trois côtés.
 */
public class Triangle extends Polygone {
    private Point pointA;
    private Point pointB;
    private Point pointC;

    /**
     * Constructeur de la classe Triangle.
     * @param pointA Le premier sommet du triangle.
     * @param pointB Le deuxième sommet du triangle.
     * @param pointC Le troisième sommet du triangle.
     * @param nom Le nom du triangle.
     */
    public Triangle(Point pointA, Point pointB, Point pointC, String nom) {
        // Appelle le constructeur de la classe mère (Polygone) avec le nom donné.
        super(nom);
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    /**
     * Obtient le premier sommet du triangle.
     * @return Le premier sommet du triangle.
     */
    public Point getPointA() {
        return pointA;
    }

    /**
     * Définit le premier sommet du triangle.
     * @param pointA Le nouveau premier sommet du triangle.
     */
    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    /**
     * Obtient le deuxième sommet du triangle.
     * @return Le deuxième sommet du triangle.
     */
    public Point getPointB() {
        return pointB;
    }

    /**
     * Définit le deuxième sommet du triangle.
     * @param pointB Le nouveau deuxième sommet du triangle.
     */
    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    /**
     * Obtient le troisième sommet du triangle.
     * @return Le troisième sommet du triangle.
     */
    public Point getPointC() {
        return pointC;
    }

    /**
     * Définit le troisième sommet du triangle.
     * @param pointC Le nouveau troisième sommet du triangle.
     */
    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    /**
     * Méthode permettant à un visiteur de traiter ce triangle.
     * @param visitor Le visiteur à appliquer.
     * @param <T> Le type de graphique retourné par le visiteur.
     * @return Le résultat de la visite.
     */
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
