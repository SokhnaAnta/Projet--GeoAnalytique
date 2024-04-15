package geoanalytique.graphique ;
import java.awt.Graphics;

public class GOvale extends Graphique {
    private int largeur, hauteur; // Dimensions de l'ovale

    public GOvale(int x, int y, int largeur, int hauteur,String nom) {
        super(x, y,nom);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public void dessiner(Graphics g) {
        g.drawOval(x, y, largeur, hauteur); // Dessiner un ovale aux coordonnées spécifiées
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
    @Override
    public boolean contientPoint(int px, int py) {
        // Calculer les coordonnées du centre de l'ovale
        int centerX = x + largeur / 2;
        int centerY = y + hauteur / 2;
    
        // Calculer les rayons sur les axes x et y
        int rx = largeur / 2;
        int ry = hauteur / 2;
    
        // Vérifier si le point (px, py) est à l'intérieur de l'ovale
        // (px - centerX)^2 / rx^2 + (py - centerY)^2 / ry^2 <= 1
        if (rx == 0 || ry == 0) {
            return false; // Évite la division par zéro, l'ovale ne peut pas être une ligne ou un point
        }
    
        // Calcul de l'équation de l'ellipse pour le point donné
        double equation = Math.pow((px - centerX) / (double) rx, 2) + Math.pow((py - centerY) / (double) ry, 2);
    
        return equation <= 1.0;
    }
    
    
}