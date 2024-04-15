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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
