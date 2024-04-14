package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.util.Operation;

public class DeplacerPointOperation implements Operation{
    private Point point;
    private double newX;
    private double newY;

    @Override
    public String getTitle() {
        return "Déplacer le point";
    }

    @Override
    public int getArite() {
        return 3; // 3 arguments : le point à déplacer, la nouvelle coordonnée X, la nouvelle coordonnée Y
    }

    @Override
    public void setArgument(int num, Object o) {
        if (getClassArgument(num).equals(Point.class)) {
            this.point = (Point) o;
        } else if (num == 2 && getClassArgument(num).equals(Double.class)) {
            this.newX = (Double) o;
        } else if (num == 3 && getClassArgument(num).equals(Double.class)) {
            this.newY = (Double) o;
        }
    }

    @Override
    public Class getClassArgument(int num) {
        if (num == 1) {
            return Point.class ;
        } else {
            return Double.class;
        }
    }

    @Override
    public Object calculate() {
        if (point != null) {
            point.setAbscisse(newX) ;
            point.setOrdonnee(newY);
        }
        return point;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Point à déplacer";
        } else if (num == 2) {
            return "Nouvelle coordonnée X";
        } else {
            return "Nouvelle coordonnée Y";
        }
    }
    
}
