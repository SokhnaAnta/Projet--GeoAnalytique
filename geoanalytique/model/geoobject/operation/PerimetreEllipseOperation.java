package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Ellipse;
import geoanalytique.util.Operation;

public class PerimetreEllipseOperation implements Operation {
    private Ellipse ellipse;

    @Override
    public String getTitle() {
        return "Calculer le périmètre de l'ellipse";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : l'ellipse dont on veut calculer le périmètre
    }

    @Override
    public void setArgument(int num, Object o) {
        if (num == 1 && o instanceof Ellipse) {
            this.ellipse = (Ellipse) o;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) {
        return Ellipse.class;
    }

    @Override
    public Object calculate() {
        double perimetre = 0.0;

        if (ellipse != null) {
            // Calcul du périmètre de l'ellipse (approximation)
            double a = ellipse.getRayonX();
            double b = ellipse.getRayonY();
            double h = Math.pow((a - b) / (a + b), 2);
            perimetre = Math.PI * (a + b) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
        }

        return perimetre;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Ellipse dont on veut calculer le périmètre";
        }
        return null;
    }
}
