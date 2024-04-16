package geoanalytique.graphique ;
import java.awt.Graphics;

/**
 * Classe GCoordonnee représentant un point graphique sur un canevas.
 */
public class GCoordonnee extends Graphique {
     /**
     * Constructeur pour créer une coordonnée graphique.
     * @param x La position x du point sur le canevas.
     * @param y La position y du point sur le canevas.
     * @param nom Le nom associé à ce point graphique.
     */
    
    public GCoordonnee(int x, int  y,String nom) {
        super(x, y,nom);
    }



     /**
     * Méthode pour dessiner le point sur le canevas.
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    public void dessiner(Graphics g) {
        int diametre = 7;
        g.fillOval(x - diametre / 2, y - diametre / 2, diametre, diametre);
    }
    
     /**
     * Méthode pour déterminer si un clic de souris est sur ce point.
     * @param x La position x du clic de souris.
     * @param y La position y du clic de souris.
     * @return true si le clic est à l'intérieur de la zone de tolérance du point.
     */
    @Override
    public boolean contientPoint(int x, int y) {
       return  this.x == x && this.y == y ? true : false;
        
    }

}