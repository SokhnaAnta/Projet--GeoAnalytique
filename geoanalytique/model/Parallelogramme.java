package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

/**
 * La classe Parallelogramme représente un parallélogramme dans un espace géométrique.
 */
public class Parallelogramme extends Polygone {
    private Point pointA;
    private Point pointB;
    private Point pointC;
    private Point pointD;

    /**
     * Constructeur de la classe Parallelogramme.
     * @param pointA Le premier point du parallélogramme.
     * @param pointB Le deuxième point du parallélogramme.
     * @param pointC Le troisième point du parallélogramme.
     * @param pointD Le quatrième point du parallélogramme.
     * @param nom Le nom du parallélogramme.
     */
    public Parallelogramme(Point pointA, Point pointB, Point pointC, Point pointD, String nom) {
        // Appelle le constructeur de la classe mère (Polygone) avec le nom donné.
        super(nom);
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
    }

    /**
     * Obtient le premier point du parallélogramme.
     * @return Le premier point du parallélogramme.
     */
    public Point getPointA() {
        return pointA;
    }

    /**
     * Définit le premier point du parallélogramme.
     * @param pointA Le nouveau premier point du parallélogramme.
     */
    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    /**
     * Obtient le deuxième point du parallélogramme.
     * @return Le deuxième point du parallélogramme.
     */
    public Point getPointB() {
        return pointB;
    }

    /**
     * Définit le deuxième point du parallélogramme.
     * @param pointB Le nouveau deuxième point du parallélogramme.
     */
    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }
    
    /**
     * Obtient le troisième point du parallélogramme.
     * @return Le troisième point du parallélogramme.
     */
    public Point getPointC() {
        return pointC;
    }

    /**
     * Définit le troisième point du parallélogramme.
     * @param pointC Le nouveau troisième point du parallélogramme.
     */
    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    /**
     * Obtient le quatrième point du parallélogramme.
     * @return Le quatrième point du parallélogramme.
     */
    public Point getPointD() {
        return pointD;
    }

    /**
     * Définit le quatrième point du parallélogramme.
     * @param pointD Le nouveau quatrième point du parallélogramme.
     */
    public void setPointD(Point pointD) {
        this.pointD = pointD;
    }

    /**
     * Méthode permettant à un visiteur de traiter ce parallélogramme.
     * @param visitor Le visiteur à appliquer.
     * @param <T> Le type de graphique retourné par le visiteur.
     * @return Le résultat de la visite.
     */
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
