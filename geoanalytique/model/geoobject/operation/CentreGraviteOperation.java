package geoanalytique.model.geoobject.operation;

import geoanalytique.exception.ArgumentOperationException;
import geoanalytique.model.GeoObject;
import geoanalytique.util.Operation;
import geoanalytique.model.*;

/**
 * Opération pour calculer le centre de gravité d'un objet géométrique.
 */
public class CentreGraviteOperation implements Operation {
    private GeoObject geoObject; // L'objet pour lequel calculer le centre de gravité

    /**
     * Constructeur de l'opération de calcul du centre de gravité.
     * 
     * @param geoObject L'objet géométrique pour lequel calculer le centre de
     *                  gravité.
     */
    public CentreGraviteOperation(GeoObject geoObject) {
        this.geoObject = geoObject;
    }

    @Override
    public String getTitle() {
        return "Calculer le centre de gravité";
    }

    @Override
    public int getArite() {
        // Cette opération prend un argument supplémentaire (l'objet géométrique)
        return 1;
    }

    @Override
    public void setArgument(int num, Object o) throws ArgumentOperationException {
        if (num == 1 && o instanceof GeoObject) {
            this.geoObject = (GeoObject) o;
        } else {
            throw new ArgumentOperationException("Argument invalide pour CentreGraviteOperation.");
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) throws ArgumentOperationException {
        if (num == 1) {
            return GeoObject.class;
        } else {
            throw new ArgumentOperationException("Argument invalide pour CentreGraviteOperation.");
        }
    }

    @Override
    public Point calculate() {
        // Vérifiez si l'objet géométrique est un parallélogramme
        if (geoObject instanceof Parallelogramme) {
            Parallelogramme parallelogramme = (Parallelogramme) geoObject;

            // Récupérez les sommets du parallélogramme
            Point pointA = parallelogramme.getPointA();
            Point pointB = parallelogramme.getPointB();
            Point pointC = parallelogramme.getPointC();
            Point pointD = parallelogramme.getPointD();

            // Calculez les coordonnées du centre de gravité (moyenne des coordonnées des
            // sommets)
            double centerX = (pointA.getAbscisse() + pointB.getAbscisse() + pointC.getAbscisse() + pointD.getAbscisse())
                    / 4.0;
            double centerY = (pointA.getOrdonnee() + pointB.getOrdonnee() + pointC.getOrdonnee() + pointD.getOrdonnee())
                    / 4.0;

            // Créez un nouveau point pour représenter le centre de gravité
            return new Point(centerX, centerY, "Centre de gravité");
        } else if (geoObject instanceof Triangle) { // Vérifiez si l'objet géométrique est un triangle
            Triangle triangle = (Triangle) geoObject;

            // Récupérez les sommets du triangle
            Point pointA = triangle.getPointA();
            Point pointB = triangle.getPointB();
            Point pointC = triangle.getPointC();

            // Calculez les coordonnées du centre de gravité (moyenne des coordonnées des
            // sommets)
            double centerX = (pointA.getAbscisse() + pointB.getAbscisse() + pointC.getAbscisse()) / 3.0;
            double centerY = (pointA.getOrdonnee() + pointB.getOrdonnee() + pointC.getOrdonnee()) / 3.0;

            // Créez un nouveau point pour représenter le centre de gravité
            return new Point(centerX, centerY, "Centre de gravité");
        } else if (geoObject instanceof Ellipse) { // Vérifiez si l'objet géométrique est une ellipse
            Ellipse ellipse = (Ellipse) geoObject;

            // Centre de gravite d'une ellipse est son centre tout court
            return ellipse.getCentre();
        } else {
            // Si l'objet n'est ni un parallélogramme, ni un triangle, ni une ellipse
            throw new UnsupportedOperationException(
                    "Calcul du centre de gravité non pris en charge pour cet objet géométrique.");
        }
    }

    @Override
    public String getDescriptionArgument(int num) throws ArgumentOperationException {
        if (num == 1) {
            return "Objet géométrique pour lequel calculer le centre de gravité";
        } else {
            throw new ArgumentOperationException("Argument invalide pour CentreGraviteOperation.");
        }
    }
}
