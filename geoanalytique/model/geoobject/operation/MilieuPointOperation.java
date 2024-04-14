package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.util.Operation;

public class MilieuPointOperation implements Operation {
    private Point point1;
    private Point point2;
    private Point milieu;

    @Override
    public String getTitle() {
        return "Calculer le milieu de deux points";
    }

    @Override
    public int getArite() {
        return 2; 
    }

    @Override
    public void setArgument(int num, Object o) {
        if (getClassArgument(num).equals(Point.class)) {
            if (num == 1) {
                this.point1 = (Point) o;
            } else {
                this.point2 = (Point) o;
            }
        }
    }

    @Override
    public Class getClassArgument(int num) {
        return Point.class;
    }

    @Override
    public Object calculate() {
        if (point1 != null && point2 != null) {
            double midX = (point1.getAbscisse() + point2.getAbscisse()) / 2;
            double midY = (point1.getOrdonnee() + point2.getOrdonnee()) / 2;
            milieu = new Point(midX, midY,"Milieu");
            return milieu;
        }
        return null; // Retourne null si les points ne sont pas correctement définis
    }

    @Override
    public String getDescriptionArgument(int num) {
        return "Point " + num;
    }
}
