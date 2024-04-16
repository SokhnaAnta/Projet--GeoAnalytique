package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.model.Triangle;
import geoanalytique.util.Operation;

public class CercleInscritTriangleOperation implements Operation {
    private Triangle triangle;

    @Override
    public String getTitle() {
        return "Trouver le cercle inscrit au triangle";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : le triangle pour lequel trouver le cercle inscrit
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
            // Calcul des longueurs des côtés du triangle
            DistancePoint distanceAB = new DistancePoint(triangle.getPointA(), triangle.getPointB());
            double a = (double) distanceAB.calculate();

            DistancePoint distanceBC = new DistancePoint(triangle.getPointB(), triangle.getPointC());
            double b = (double) distanceBC.calculate();

            DistancePoint distanceCA = new DistancePoint(triangle.getPointC(), triangle.getPointA());
            double c = (double) distanceCA.calculate();

            // Calcul du demi-périmètre
            double s = (a + b + c) / 2.0;

            // Calcul du rayon du cercle inscrit
            double radius = Math.sqrt((s - a) * (s - b) * (s - c) / s);

            // Calcul du centre du cercle inscrit (intersection des bissectrices)
            double x = (a * triangle.getPointC().getAbscisse() + b * triangle.getPointA().getAbscisse()
                    + c * triangle.getPointB().getAbscisse()) / (a + b + c);
            double y = (a * triangle.getPointC().getOrdonnee() + b * triangle.getPointA().getOrdonnee()
                    + c * triangle.getPointB().getOrdonnee()) / (a + b + c);

            return new Point(x, y, "Centre du cercle inscrit");
        }
        return null;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Triangle pour lequel trouver le cercle inscrit";
        }
        return null;
    }
}
