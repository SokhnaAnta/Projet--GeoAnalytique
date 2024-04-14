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
    public boolean contientPoint(int x, int y) {
        boolean result = false;
        int j = nPoints - 1; // Le dernier sommet est le précédent du premier
        for (int i = 0; i < nPoints; i++) {
            if ((yPoints[i] > y) != (yPoints[j] > y) &&
                (x < (xPoints[j] - xPoints[i]) * (y - yPoints[i]) / (yPoints[j] - yPoints[i]) + xPoints[i])) {
                result = !result;
            }
            j = i;
        }
        return result;
    }
}
