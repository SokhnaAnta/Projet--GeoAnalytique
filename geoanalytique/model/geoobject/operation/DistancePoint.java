package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.util.Operation;

public class DistancePoint implements Operation {
    private Point point1;
    private Point point2;
    private double distance;

    // Nouveau constructeur prenant les deux points et calculant la distance
    public DistancePoint(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.calculate(); // Calcul de la distance lors de la création de l'objet
    }

    @Override
    public String getTitle() {
        return "Calculer la distance entre deux points";
    }

    @Override
    public int getArite() {
        return 2; 
    }

    @Override
    public void setArgument(int num, Object o) {
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) {
        return null;
    }

    @Override
    public Object calculate() {
        double deltaX = point2.getAbscisse() - point1.getAbscisse();
        double deltaY = point2.getOrdonnee() - point1.getOrdonnee();
        distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    @Override
    public String getDescriptionArgument(int num) {
        return null;
    }

    public double getDistance() {
        return distance;
    }
}
