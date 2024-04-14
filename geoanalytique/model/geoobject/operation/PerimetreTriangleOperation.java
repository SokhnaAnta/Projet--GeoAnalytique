package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.model.Triangle;
import geoanalytique.util.Operation;

public class PerimetreTriangleOperation implements Operation {
    private Triangle triangle;

    @Override
    public String getTitle() {
        return "Calculer le périmètre du triangle";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : le triangle dont on veut calculer le périmètre
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
        double perimetre = 0.0;

        if (triangle != null) {
            // Récupération des points du triangle
            Point pointA = triangle.getPointA();
            Point pointB = triangle.getPointB();
            Point pointC = triangle.getPointC();

            // Calcul des longueurs des côtés
            DistancePoint distanceAB = new DistancePoint(pointA, pointB);
            double coteAB = (double) distanceAB.calculate();

            DistancePoint distanceBC = new DistancePoint(pointB, pointC);
            double coteBC = (double) distanceBC.calculate();

            DistancePoint distanceCA = new DistancePoint(pointC, pointA);
            double coteCA = (double) distanceCA.calculate();

            // Somme des longueurs des côtés pour obtenir le périmètre
            perimetre = coteAB + coteBC + coteCA;
        }

        return perimetre;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Triangle dont on veut calculer le périmètre";
        }
        return null;
    }
}
