package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

/**
 * La classe Point représente un point dans un espace géométrique.
 */
public class Point extends GeoObject {
    private double abscisse;
    private double ordonnee;

    /**
     * Constructeur de la classe Point.
     * @param abscisse La coordonnée en abscisse du point.
     * @param ordonnee La coordonnée en ordonnée du point.
     * @param nom Le nom du point.
     */
    public Point(double abscisse, double ordonnee, String nom) {
        // Appelle le constructeur de la classe mère (GeoObject) avec le nom donné.
        super(nom);
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    /**
     * Obtient la coordonnée en abscisse du point.
     * @return La coordonnée en abscisse du point.
     */
    public double getAbscisse() {
        return abscisse;
    }

    /**
     * Définit la coordonnée en abscisse du point.
     * @param abscisse La nouvelle coordonnée en abscisse du point.
     */
    public void setAbscisse(double abscisse) {
        this.abscisse = abscisse;
    }

    /**
     * Obtient la coordonnée en ordonnée du point.
     * @return La coordonnée en ordonnée du point.
     */
    public double getOrdonnee() {
        return ordonnee;
    }

    /**
     * Définit la coordonnée en ordonnée du point.
     * @param ordonnee La nouvelle coordonnée en ordonnée du point.
     */
    public void setOrdonnee(double ordonnee) {
        this.ordonnee = ordonnee;
    }

    /**
     * Méthode permettant à un visiteur de traiter ce point.
     * @param visitor Le visiteur à appliquer.
     * @param <T> Le type de graphique retourné par le visiteur.
     * @return Le résultat de la visite.
     */
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
