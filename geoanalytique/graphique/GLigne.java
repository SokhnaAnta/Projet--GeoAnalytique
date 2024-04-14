package geoanalytique.graphique ;
import java.awt.Graphics;

public class GLigne extends Graphique {
    private int x2, y2; // Coordonnées du deuxième point de la ligne

    public GLigne(int x1, int y1, int x2, int y2,String nom) {
        super(x1, y1,nom);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void dessiner(Graphics g) {
        g.drawLine(x, y, x2, y2); // Dessiner une ligne entre deux points
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public boolean contientPoint(int x, int y) {

        double debut = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        double fin = Math.sqrt(Math.pow(x - this.x2, 2) + Math.pow(y - this.y2, 2));
        double lineLength = Math.sqrt(Math.pow(this.x2 - this.x, 2) + Math.pow(this.y2 - this.y, 2));
    
        double temp = 0.1;  
        if (debut + fin - lineLength > temp) {
            return false;
        }
    
        return true;    }
}