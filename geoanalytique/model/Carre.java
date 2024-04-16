package geoanalytique.model;

/**
 * La classe Carre représente un carré, qui est un type spécial de parallélogramme
 * avec des côtés égaux et des angles droits.
 */
public class Carre extends Parallelogramme {

    /**
     * Constructeur de la classe Carre.
     * @param pointA Le premier point du carré.
     * @param pointB Le deuxième point du carré.
     * @param pointC Le troisième point du carré.
     * @param pointD Le quatrième point du carré.
     * @param nom Le nom du carré.
     */
    public Carre(Point pointA, Point pointB, Point pointC, Point pointD, String nom) {
        // Appelle le constructeur de la classe mère (Parallelogramme) avec les mêmes paramètres.
        super(pointA, pointB, pointC, pointD, nom);
    }
    
}
