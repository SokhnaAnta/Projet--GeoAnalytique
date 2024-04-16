package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

/**
 * La classe Droite représente une droite dans un espace géométrique.
 */
public class Droite extends GeoObject {
    private Point pointA;
    private Point pointB;

    /**
     * Constructeur de la classe Droite.
     * @param pointA Le premier point de la droite.
     * @param pointB Le deuxième point de la droite.
     * @param nom Le nom de la droite.
     */
    public Droite(Point pointA, Point pointB, String nom) {
        // Appelle le constructeur de la classe mère (GeoObject) avec le nom donné.
        super(nom);
        this.pointA = pointA;
        this.pointB = pointB;
    }

    /**
     * Obtient le premier point de la droite.
     * @return Le premier point de la droite.
     */
    public Point getPointA() {
        return pointA;
    }

    /**
     * Définit le premier point de la droite.
     * @param pointA Le nouveau premier point de la droite.
     */
    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    /**
     * Obtient le deuxième point de la droite.
     * @return Le deuxième point de la droite.
     */
    public Point getPointB() {
        return pointB;
    }

    /**
     * Définit le deuxième point de la droite.
     * @param pointB Le nouveau deuxième point de la droite.
     */
    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }
    
    /**
     * Définit les points de la droite en spécifiant un nouveau premier point et un nouveau deuxième point.
     * @param nouveauPointA Le nouveau premier point de la droite.
     * @param nouveauPointB Le nouveau deuxième point de la droite.
     */
    public void setPoints(Point nouveauPointA, Point nouveauPointB) {
        this.pointA = nouveauPointA;
        this.pointB = nouveauPointB;
    }

    /**
     * Méthode permettant à un visiteur de traiter cette droite.
     * @param visitor Le visiteur à appliquer.
     * @param <T> Le type de graphique retourné par le visiteur.
     * @return Le résultat de la visite.
     */
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
