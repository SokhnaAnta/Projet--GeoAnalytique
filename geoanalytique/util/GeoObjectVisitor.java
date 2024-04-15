package geoanalytique.util;

import geoanalytique.model.*;


import geoanalytique.graphique.Graphique;

public interface GeoObjectVisitor<T extends Graphique> {
    T visit(Texte texte);
    T visit(Point point);
    T visit(Droite droite);
    T visit(Ellipse ellipse);
    T visit(Cercle cercle);
    T visit(Triangle triangle);
    T visit(Parallelogramme parallelogramme);
}

