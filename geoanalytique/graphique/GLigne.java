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


    public boolean contientPoint(int px, int py) {
        double dist;
    
        // Longueur du segment de ligne
        double longueur = Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
        if (longueur == 0) return false; // Le segment de ligne est un point
    
        // Utilisation de la formule pour la distance point-ligne
        dist = Math.abs((px - x) * (y2 - y) - (py - y) * (x2 - x)) / longueur;
    
        // Définir un seuil de distance pour considérer que le point "touche" la ligne
        final double seuil = 5.0; // pixels
    
        return dist <= seuil;
    }
}