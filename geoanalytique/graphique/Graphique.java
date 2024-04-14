package geoanalytique.graphique;

import java.awt.Graphics;

public abstract class Graphique {
    protected int x, y; 
    protected String nom ;
    
    public Graphique( int x, int y, String nom) {
        this.x = x;
        this.y = y;
        this.nom = nom ;
    }

    public abstract void dessiner(Graphics g);
    public abstract boolean contientPoint(int x , int y) ;
}
