package geoanalytique.model;

/**
 * La classe Rectangle représente un rectangle, qui est un type spécial de parallélogramme
 * avec des angles droits et des côtés opposés de même longueur.
 */
public class Rectangle extends Parallelogramme {

    /**
     * Constructeur de la classe Rectangle.
     * @param pointA Le premier point du rectangle.
     * @param pointB Le deuxième point du rectangle.
     * @param pointC Le troisième point du rectangle.
     * @param pointD Le quatrième point du rectangle.
     * @param nom Le nom du rectangle.
     */
    public Rectangle(Point pointA, Point pointB, Point pointC, Point pointD, String nom) {
        // Appelle le constructeur de la classe mère (Parallelogramme) avec les mêmes paramètres.
        super(pointA, pointB, pointC, pointD, nom);
    }
    
}
