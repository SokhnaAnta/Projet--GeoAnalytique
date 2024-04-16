package geoanalytique.util;

import geoanalytique.graphique.Graphique;
import geoanalytique.model.*;

/**
 * Interface pour le pattern visiteur, appliqué aux objets géométriques.
 * Permet l'implémentation de visiteurs qui peuvent effectuer des opérations
 * sur les différents types de GeoObject, en retournant un résultat de type Graphique.
 */
public interface GeoObjectVisitor<T extends Graphique> {
    
    /**
     * Visite et traite un objet de type Texte.
     * @param texte L'objet Texte à visiter.
     * @return Une instance de T, représentant graphiquement le texte.
     */
    T visit(Texte texte);

    /**
     * Visite et traite un objet de type Point.
     * @param point L'objet Point à visiter.
     * @return Une instance de T, représentant graphiquement le point.
     */
    T visit(Point point);

    /**
     * Visite et traite un objet de type Droite.
     * @param droite L'objet Droite à visiter.
     * @return Une instance de T, représentant graphiquement la droite.
     */
    T visit(Droite droite);

    /**
     * Visite et traite un objet de type Ellipse.
     * @param ellipse L'objet Ellipse à visiter.
     * @return Une instance de T, représentant graphiquement l'ellipse.
     */
    T visit(Ellipse ellipse);

    /**
     * Visite et traite un objet de type Cercle.
     * @param cercle L'objet Cercle à visiter.
     * @return Une instance de T, représentant graphiquement le cercle.
     */
    T visit(Cercle cercle);

    /**
     * Visite et traite un objet de type Triangle.
     * @param triangle L'objet Triangle à visiter.
     * @return Une instance de T, représentant graphiquement le triangle.
     */
    T visit(Triangle triangle);

    /**
     * Visite et traite un objet de type Parallelogramme.
     * @param parallelogramme L'objet Parallelogramme à visiter.
     * @return Une instance de T, représentant graphiquement le parallelogramme.
     */
    T visit(Parallelogramme parallelogramme);

    
}
