package geoanalytique.graphique ;
import java.awt.Graphics;

/**
 * Classe représentant un polygone graphique.
 */

public class GPolygone extends Graphique {
    private int[] xPoints; // Tableau des coordonnées x des points du polygone
    private int[] yPoints; // Tableau des coordonnées y des points du polygone
    private int nPoints;   // Nombre de points du polygone

     /**
     * Constructeur pour créer un polygone graphique.
     * @param x Position x pour le placement du nom ou d'autres annotations.
     * @param y Position y pour le placement du nom ou d'autres annotations.
     * @param xPoints Tableau des coordonnées x des sommets du polygone.
     * @param yPoints Tableau des coordonnées y des sommets du polygone.
     * @param nPoints Nombre de sommets du polygone.
     * @param nom Le nom du polygone.
     */

    public GPolygone(int x, int y, int[] xPoints, int[] yPoints, int nPoints,String nom) {
        super(x, y,nom); 
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.nPoints = nPoints;
    }

    /**
     * Dessine le polygone sur un contexte graphique.
     * @param g L'objet Graphics utilisé pour le dessin.
     */

    @Override
    public void dessiner(Graphics g) {
        g.drawPolygon(xPoints, yPoints, nPoints);
    }


    /**
     * Détermine si un point spécifié est à l'intérieur du polygone.
     * Utilise l'algorithme de tracé non-zéro pour vérifier si le point (px, py) est contenu dans le polygone.
     * @param px La coordonnée x du point à tester.
     * @param py La coordonnée y du point à tester.
     * @return true si le point est à l'intérieur du polygone, false autrement.
     */

    @Override
    public boolean contientPoint(int px, int py) {
    boolean result = false;
    int j = nPoints - 1;  // Dernier vertice

    for (int i = 0; i < nPoints; i++) {
        if ((yPoints[i] < py && yPoints[j] >= py) || (yPoints[j] < py && yPoints[i] >= py)) {
            if (xPoints[i] + (py - yPoints[i]) / (double) (yPoints[j] - yPoints[i]) * (xPoints[j] - xPoints[i]) < px) {
                result = !result;
            }
        }
        j = i; // j est maintenant le précédent i pour la prochaine itération
    }

    return result;
}

}
