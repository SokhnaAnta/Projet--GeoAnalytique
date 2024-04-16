package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.model.Triangle;
import geoanalytique.util.Operation;

public class CercleCirconscritTriangleOperation implements Operation {
    private Triangle triangle;

    @Override
    public String getTitle() {
        return "Trouver le cercle circonscrit au triangle";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : le triangle pour lequel trouver le cercle circonscrit
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

            // Calcul du rayon du cercle circonscrit
            double radius = (a * b * c) / (4 * Math.sqrt(s * (s - a) * (s - b) * (s - c)));

            // Calcul du centre du cercle circonscrit (intersection des médiatrices)
            double x = ((triangle.getPointA().getAbscisse() + triangle.getPointB().getAbscisse())
                    * (triangle.getPointA().getOrdonnee() - triangle.getPointC().getOrdonnee())
                    - (triangle.getPointA().getAbscisse() + triangle.getPointC().getAbscisse())
                            * (triangle.getPointA().getOrdonnee() - triangle.getPointB().getOrdonnee()))
                    / (2 * (triangle.getPointA().getOrdonnee() - triangle.getPointC().getOrdonnee())
                            * (triangle.getPointA().getAbscisse() - triangle.getPointC().getAbscisse())
                            - 2 * (triangle.getPointA().getOrdonnee() - triangle.getPointB().getOrdonnee())
                                    * (triangle.getPointA().getAbscisse() - triangle.getPointB().getAbscisse()));

            double y = ((triangle.getPointA().getOrdonnee() + triangle.getPointB().getOrdonnee())
                    * (triangle.getPointA().getAbscisse() - triangle.getPointC().getAbscisse())
                    - (triangle.getPointA().getOrdonnee() + triangle.getPointC().getOrdonnee())
                            * (triangle.getPointA().getAbscisse() - triangle.getPointB().getAbscisse()))
                    / (2 * (triangle.getPointA().getOrdonnee() - triangle.getPointC().getOrdonnee())
                            * (triangle.getPointA().getAbscisse() - triangle.getPointC().getAbscisse())
                            - 2 * (triangle.getPointA().getOrdonnee() - triangle.getPointB().getOrdonnee())
                                    * (triangle.getPointA().getAbscisse() - triangle.getPointB().getAbscisse()));

            return new Point(x, y, "Centre du cercle circonscrit");
        }
        return null;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Triangle pour lequel trouver le cercle circonscrit";
        }
        return null;
    }
}
