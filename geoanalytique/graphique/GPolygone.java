package geoanalytique.graphique ;
import java.awt.Graphics;

public class GPolygone extends Graphique {
    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;  // Nombre de points

    public GPolygone(int x, int y, int[] xPoints, int[] yPoints, int nPoints,String nom) {
        super(x, y,nom); 
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.nPoints = nPoints;
    }

    @Override
    public void dessiner(Graphics g) {
        g.drawPolygon(xPoints, yPoints, nPoints);
    }

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
