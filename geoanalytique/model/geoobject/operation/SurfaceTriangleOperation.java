package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Triangle;
import geoanalytique.util.Operation;
import geoanalytique.model.Point;

/**
 * Opération pour calculer la surface d'un triangle.
 */
public class SurfaceTriangleOperation implements Operation {
    private Triangle triangle;

    @Override
    public String getTitle() {
        return "Calculer la surface du triangle";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : le triangle pour lequel calculer la surface
    }

    @Override
    public void setArgument(int num, Object o) {
        if (num == 1 && o instanceof Triangle) {
            this.triangle = (Triangle) o;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) {
        return Triangle.class;
    }

    @Override
    public Object calculate() {
        if (triangle != null) {
            // Obtention des longueurs des côtés du triangle
            double a = calculateDistance(triangle.getPointA(), triangle.getPointB());
            double b = calculateDistance(triangle.getPointB(), triangle.getPointC());
            double c = calculateDistance(triangle.getPointC(), triangle.getPointA());

            // Calcul de la surface du triangle en utilisant la formule de Héron
            double s = (a + b + c) / 2.0;
            double surface = Math.sqrt(s * (s - a) * (s - b) * (s - c));
            return surface;
        }
        return null;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Triangle pour lequel calculer la surface";
        }
        return null;
    }

    // Méthode pour calculer la distance entre deux points
    private double calculateDistance(Point point1, Point point2) {
        double deltaX = point2.getAbscisse() - point1.getAbscisse();
        double deltaY = point2.getOrdonnee() - point1.getOrdonnee();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
