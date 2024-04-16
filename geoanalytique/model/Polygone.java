package geoanalytique.model;

/**
 * La classe Polygone représente un polygone dans un espace géométrique.
 * Elle étend la classe Surface, ce qui signifie qu'elle représente une forme géométrique bidimensionnelle.
 * Les polygones sont des formes composées de segments de lignes connectés.
 */
public abstract class Polygone extends Surface {

    /**
     * Constructeur de la classe Polygone.
     * @param nom Le nom du polygone.
     */
    public Polygone(String nom) {
        // Appelle le constructeur de la classe mère (Surface) avec le nom donné.
        super(nom);
    }
   
}
