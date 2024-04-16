package geoanalytique.model;

/**
 * La classe Surface représente une surface dans un espace géométrique.
 * Elle est abstraite car elle ne définit pas directement une forme géométrique spécifique,
 * mais sert de classe de base pour des formes géométriques bidimensionnelles.
 */
public abstract class Surface extends GeoObject {

    /**
     * Constructeur de la classe Surface.
     * @param nom Le nom de la surface.
     */
    public Surface(String nom) {
        // Appelle le constructeur de la classe mère (GeoObject) avec le nom donné.
        super(nom);
    }
    
}
