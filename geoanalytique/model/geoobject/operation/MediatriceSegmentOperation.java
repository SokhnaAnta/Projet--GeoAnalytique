package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.model.Segment;
import geoanalytique.util.Operation;

public class MediatriceSegmentOperation implements Operation {
    private Segment segment;

    @Override
    public String getTitle() {
        return "Construire la médiatrice du segment";
    }

    @Override
    public int getArite() {
        return 1; // 1 argument : le segment dont on veut construire la médiatrice
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
            // Calcul des coordonnées du milieu du segment
            Point pointA = segment.getPointA();
            Point pointB = segment.getPointB();
            double milieuX = (pointA.getAbscisse() + pointB.getAbscisse()) / 2.0;
            double milieuY = (pointA.getOrdonnee() + pointB.getOrdonnee()) / 2.0;

            // Calcul de la pente perpendiculaire à celle du segment
            double pentePerpendiculaire = -1.0 /(double) new PenteSegmentOperation().calculate();

            // Calcul de l'ordonnée à l'origine de la médiatrice
            double ordonneeOrigine = milieuY - pentePerpendiculaire * milieuX;

            // Construction du point de départ de la médiatrice
            Point pointDepart = new Point(milieuX - 100, pentePerpendiculaire * (milieuX - 100) + ordonneeOrigine,"Point de depart");

            // Construction du point d'arrivée de la médiatrice
            Point pointArrivee = new Point(milieuX + 100, pentePerpendiculaire * (milieuX + 100) + ordonneeOrigine,"Point de depart");

            // Construction de la médiatrice
            return new Segment(pointDepart, pointArrivee,"nouveau segment");
        }
        return null;
    }

    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Segment dont on veut construire la médiatrice";
        }
        return null;
    }
}
