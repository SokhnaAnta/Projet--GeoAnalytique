package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Point;
import geoanalytique.util.Operation;


/**
 * Opération pour déplacer un point dans le plan 2D.
 */

public class DeplacerPointOperation implements Operation{
    private Point point;
    private double newX;
    private double newY;

    /**
     * Retourne le titre de l'opération.
     * @return Le titre de l'opération.
     */

    @Override
    public String getTitle() {
        return "Déplacer le point";
    }

    /**
     * Retourne le nombre d'arguments nécessaires pour cette opération.
     * @return Le nombre d'arguments.
     */
    @Override
    public int getArite() {
        return 3; // 3 arguments : le point à déplacer, la nouvelle coordonnée X, la nouvelle coordonnée Y
    }

    /**
     * Définit un argument pour l'opération basé sur son index.
     * @param num L'index de l'argument (commence à 1).
     * @param o L'objet argument.
     */
    @Override
    public void setArgument(int num, Object o) {
        if (getClassArgument(num).equals(Point.class)) {
            this.point = (Point) o;

        } else if (num == 2 && getClassArgument(num).equals(Double.class)) {
            this.newX = (Double) o;
        } else if (num == 3 && getClassArgument(num).equals(Double.class)) {
            this.newY = (Double) o;
        }else{
        }
    }

    /**
     * Retourne la classe de l'argument selon son numéro.
     * @param num L'index de l'argument.
     * @return La classe correspondante de l'argument.
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) {
        if (num == 1) {
            return Point.class ;
        } else {
            return Double.class;
        }
    }

     /**
     * Calcule l'opération de déplacement du point.
     * @return Le point déplacé avec les nouvelles coordonnées.
     */

    @Override
    public Object calculate() {
        if (point != null) {
            point.setAbscisse(newX) ;
            point.setOrdonnee(newY);
        }
        return point;
    }

     /**
     * Fournit une description de chaque argument basée sur son numéro.
     * @param num L'index de l'argument.
     * @return La description de l'argument.
     */

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
