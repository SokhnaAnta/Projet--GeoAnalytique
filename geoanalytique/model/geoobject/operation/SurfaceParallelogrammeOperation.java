package geoanalytique.model.geoobject.operation;

import geoanalytique.model.Parallelogramme;
import geoanalytique.util.Operation;

/**
 * Opération pour calculer la surface d'un parallélogramme.
 */
public class SurfaceParallelogrammeOperation implements Operation {
    private Parallelogramme parallelogramme;

    /**
     * Obtient le titre de l'opération.
     */
    @Override
    public String getTitle() {
        return "Calculer la surface du parallélogramme";
    }

    /**
     * Obtient le nombre d'arguments requis pour cette opération.
     */
    @Override
    public int getArite() {
        return 1; // 1 argument : le parallélogramme pour lequel calculer la surface
    }

    /**
     * Définit les arguments pour l'opération.
     */
    @Override
    public void setArgument(int num, Object o) {
        if (num == 1 && o instanceof Parallelogramme) {
            this.parallelogramme = (Parallelogramme) o;
        }
    }

    /**
     * Obtient le type de classe pour l'argument spécifié.
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Class getClassArgument(int num) {
        return Parallelogramme.class;
    }

    /**
     * Calcule la surface du parallélogramme.
     */
    @Override
    public Object calculate() {
        if (parallelogramme != null) {
            // Calcul de la surface du parallélogramme directement ici
            double baseX = parallelogramme.getPointB().getAbscisse() - parallelogramme.getPointA().getAbscisse();
            double baseY = parallelogramme.getPointB().getOrdonnee() - parallelogramme.getPointA().getOrdonnee();
            double hauteurX = parallelogramme.getPointC().getAbscisse() - parallelogramme.getPointA().getAbscisse();
            double hauteurY = parallelogramme.getPointC().getOrdonnee() - parallelogramme.getPointA().getOrdonnee();

            // Calcul de la surface en utilisant le produit vectoriel des deux côtés
            // adjacents
            double surface = Math.abs(baseX * hauteurY - baseY * hauteurX);

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
            return "Parallélogramme pour lequel calculer la surface";
        }
        return null;
    }
}
