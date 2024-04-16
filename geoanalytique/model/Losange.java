package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

/**
 * La classe Losange représente un losange, qui est un type spécial de parallélogramme
 * avec des côtés égaux et des angles adjacents égaux.
 */
public class Losange extends Parallelogramme {

    /**
     * Constructeur de la classe Losange.
     * @param pointA Le premier point du losange.
     * @param pointB Le deuxième point du losange.
     * @param pointC Le troisième point du losange.
     * @param pointD Le quatrième point du losange.
     * @param nom Le nom du losange.
     */
    public Losange(Point pointA, Point pointB, Point pointC, Point pointD, String nom) {
        // Appelle le constructeur de la classe mère (Parallelogramme) avec les mêmes paramètres.
        super(pointA, pointB, pointC, pointD, nom);
    }
    
    /**
     * Méthode permettant à un visiteur de traiter ce losange.
     * @param visitor Le visiteur à appliquer.
     * @param <T> Le type de graphique retourné par le visiteur.
     * @return Le résultat de la visite.
     */
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
}
