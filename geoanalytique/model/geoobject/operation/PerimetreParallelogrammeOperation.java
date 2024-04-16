package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Parallelogramme;
import geoanalytique.util.Operation;

public class PerimetreParallelogrammeOperation implements Operation {
    private Parallelogramme parallelogramme;

    @Override
    public String getTitle() {
        return "Calculer le périmètre du parallelogramme";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : le parallelogramme dont on veut calculer le périmètre
    }

    @Override
    public void setArgument(int num, Object o) {
        if (num == 1 && o instanceof Parallelogramme) {
            this.parallelogramme = (Parallelogramme) o;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) {
        return Parallelogramme.class;
    }

    @Override
    public Object calculate() {
        double perimetre = 0.0;

        if (parallelogramme != null) {
            // Calcul des longueurs des côtés
            DistancePoint distanceAB = new DistancePoint(parallelogramme.getPointA(), parallelogramme.getPointB());
            double longueurAB = (double) distanceAB.calculate();

            DistancePoint distanceBC = new DistancePoint(parallelogramme.getPointB(), parallelogramme.getPointC());
            double longueurBC = (double) distanceBC.calculate();

            // Calcul du périmètre
            perimetre = 2 * (longueurAB + longueurBC);
        }
        return perimetre;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Parallelogramme dont on veut calculer le périmètre";
        }
        return null;
    }
}
