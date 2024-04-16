package geoanalytique.model;

import geoanalytique.graphique.Graphique;
import geoanalytique.util.GeoObjectVisitor;

/**
 * La classe Texte représente un objet de texte dans un espace géométrique.
 */
public class Texte extends GeoObject {
    private double x;
    private double y;
    private String contenu;

    /**
     * Constructeur de la classe Texte.
     * @param x La coordonnée en abscisse du texte.
     * @param y La coordonnée en ordonnée du texte.
     * @param contenu Le contenu textuel.
     * @param nom Le nom du texte.
     */
    public Texte(double x, double y, String contenu, String nom) {
        // Appelle le constructeur de la classe mère (GeoObject) avec le nom donné.
        super(nom);
        this.x = x;
        this.y = y;
        this.contenu = contenu;
    }

    /**
     * Méthode permettant à un visiteur de traiter ce texte.
     * @param visitor Le visiteur à appliquer.
     * @param <T> Le type de graphique retourné par le visiteur.
     * @return Le résultat de la visite.
     */
    @Override
    public <T extends Graphique> T accept(GeoObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }

    // Getters et setters

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
