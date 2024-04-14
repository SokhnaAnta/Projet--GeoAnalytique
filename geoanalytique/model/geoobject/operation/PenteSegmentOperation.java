package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.model.Segment;
import geoanalytique.util.Operation;

public class PenteSegmentOperation implements Operation {
    private Segment segment;

    @Override
    public String getTitle() {
        return "Calculer la pente du segment";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : le segment dont on veut calculer la pente
    }

    @Override
    public void setArgument(int num, Object o) {
        if (num == 1 && o instanceof Segment) {
            this.segment = (Segment) o;
        }
    }

    @Override
    public Class getClassArgument(int num) {
        return Segment.class;
    }

    @Override
    public Object calculate() {
        if (segment != null) {
            // Calcul de la pente du segment
            Point pointA = segment.getPointA();
            Point pointB = segment.getPointB();
            double deltaX = pointB.getAbscisse() - pointA.getAbscisse();
            double deltaY = pointB.getOrdonnee() - pointA.getOrdonnee();
            if (deltaX != 0) {
                return deltaY / deltaX; // Pente = (variation en ordonnée) / (variation en abscisse)
            } else {
                return Double.POSITIVE_INFINITY; // Si la variation en abscisse est nulle, la pente est infinie
            }
        }
        return null;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Segment dont on veut calculer la pente";
        }
        return null;
    }
}
