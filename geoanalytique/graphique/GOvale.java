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
    public boolean contientPoint(int x, int y) {
        int centerX = this.x + largeur / 2;
        int centerY = this.y + hauteur / 2;
        return Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= largeur / 2;
    }
    
}