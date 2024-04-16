package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Ellipse;
import geoanalytique.util.Operation;

/**
 * Opération pour calculer la surface d'une ellipse.
 */
public class SurfaceEllipseOperation implements Operation {
    private Ellipse ellipse;

    /**
     * Obtient le titre de l'opération.
     */
    @Override
    public String getTitle() {
        return "Calculer la surface de l'ellipse";
    }

    /**
     * Obtient le nombre d'arguments requis pour cette opération.
     */
    @Override
    public int getArite() {
        return 1; // 1 argument : l'ellipse pour laquelle calculer la surface
    }

    /**
     * Définit les arguments pour l'opération.
     */
    @Override
    public void setArgument(int num, Object o) {
        if (num == 1 && o instanceof Ellipse) {
            this.ellipse = (Ellipse) o;
        }
    }

    /**
     * Obtient le type de classe pour l'argument spécifié.
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) {
        return Ellipse.class;
    }

    /**
     * Calcule la surface de l'ellipse.
     */
    @Override
    public Object calculate() {
        if (ellipse != null) {
            // Calcul de la surface de l'ellipse directement ici
            double a = ellipse.getRayonX() / 2.0;
            double b = ellipse.getRayonY() / 2.0;

            // Calcul de la surface de l'ellipse en utilisant la formule de l'aire d'une
            // ellipse
            double surface = Math.PI * a * b;

            return surface;
        }
        return null;
    }

    /**
     * Obtient la description de l'argument spécifié.
     */
    @Override
    public String getDescriptionArgument(int num) {
        if (num == 1) {
            return "Ellipse pour laquelle calculer la surface";
        }
        return null;
    }
}
