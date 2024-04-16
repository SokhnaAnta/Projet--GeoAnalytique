package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

/**
 * La classe Cercle représente un cercle, qui est un type spécial d'ellipse avec des rayons égaux.
 */
public class Cercle extends Ellipse {

    /**
     * Constructeur de la classe Cercle.
     * @param centre Le centre du cercle.
     * @param rayon Le rayon du cercle.
     * @param nom Le nom du cercle.
     */
    public Cercle(Point centre, double rayon, String nom) {
        // Appelle le constructeur de la classe mère (Ellipse) avec des rayons égaux.
        super(centre, rayon, rayon, nom); 
    }
    
    /**
     * Méthode permettant à un visiteur de traiter ce cercle.
     * @param visitor Le visiteur à appliquer.
     * @param <T> Le type de graphique retourné par le visiteur.
     * @return Le résultat de la visite.
     */
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
