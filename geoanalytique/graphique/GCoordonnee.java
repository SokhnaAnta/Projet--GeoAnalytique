package geoanalytique.graphique ;
import java.awt.Color;
import java.awt.Graphics;

public class GCoordonnee extends Graphique {
    
    public GCoordonnee(int x, int  y,String nom) {
        super(x, y,nom);
    }

    @Override
    public void dessiner(Graphics g) {
        int diametre = 7;
        g.setColor(Color.RED); 
        g.fillOval(x - diametre / 2, y - diametre / 2, diametre, diametre);
    }
    
@Override
public boolean contientPoint(int x, int y) {
    int hitBoxSize = 5;  
    return Math.abs(this.x - x) <= hitBoxSize && Math.abs(this.y - y) <= hitBoxSize;
}

}