package geoanalytique.graphique ;
import java.awt.Graphics;

/**
 * Classe représentant un ovale graphique.
 */

public class GOvale extends Graphique {
    private int largeur, hauteur; // Dimensions de l'ovale

    /**
     * Constructeur pour créer un ovale graphique.
     * @param x La position x du coin supérieur gauche de l'ovale.
     * @param y La position y du coin supérieur gauche de l'ovale.
     * @param largeur La largeur de l'ovale.
     * @param hauteur La hauteur de l'ovale.
     * @param nom Le nom de l'ovale.
     */

    public GOvale(int x, int y, int largeur, int hauteur,String nom) {
        super(x, y,nom);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
      

    /**
     * Dessine l'ovale sur le canevas graphique.
     * @param g L'objet Graphics utilisé pour le dessin.
     */
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



    /**
     * Vérifie si un point spécifié (px, py) est à l'intérieur de l'ovale.
     * Utilise l'équation de l'ellipse pour déterminer l'appartenance du point.
     * @param px La coordonnée x du point à vérifier.
     * @param py La coordonnée y du point à vérifier.
     * @return true si le point est à l'intérieur de l'ovale, false autrement.
     */
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