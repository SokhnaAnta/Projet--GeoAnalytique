package geoanalytique.model;

/**
 * La classe Segment représente un segment de droite dans un espace géométrique.
 * Elle est définie par deux points, pointA et pointB, qui déterminent les extrémités du segment.
 * Un segment est un cas spécial de droite avec une longueur finie.
 */
public class Segment extends Droite {

    /**
     * Constructeur de la classe Segment.
     * @param pointA Le premier point du segment.
     * @param pointB Le deuxième point du segment.
     * @param nom Le nom du segment.
     */
    public Segment(Point pointA, Point pointB, String nom) {
        // Appelle le constructeur de la classe mère (Droite) avec les mêmes paramètres.
        super(pointA, pointB, nom);
    }
  
}
