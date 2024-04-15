package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.model.Segment;
import geoanalytique.util.Operation;

public class LongueurSegmentOperation implements Operation {
    private Segment segment;

    @Override
    public String getTitle() {
        return "Calculer la longueur du segment";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : le segment dont on veut calculer la longueur
    }

    @Override
    public void setArgument(int num, Object o) {
        if (num == 1 && o instanceof Segment) {
            this.segment = (Segment) o;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) {
        return Segment.class;
    }
    @Override
    public Object calculate() {
        if (segment != null) {
            Point pointA = segment.getPointA();
            Point pointB = segment.getPointB();
            DistancePoint distanceOperation = new DistancePoint(pointA, pointB);
            return distanceOperation.calculate();
        }
        return null;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Segment dont on veut calculer la longueur";
        }
        return null;
    }
}
