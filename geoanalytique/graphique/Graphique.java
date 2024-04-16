package geoanalytique.graphique;

import java.awt.Graphics;
/**
 * Classe abstraite représentant un élément graphique de base.
 */
public abstract class Graphique {
    protected int x, y;  // Coordonnées pour le positionnement du graphique
    protected String nom;  // Nom du graphique pour identification


    /**
     * Constructeur pour initialiser un graphique avec position et nom.
     * @param x Position x du graphique.
     * @param y Position y du graphique.
     * @param nom Nom du graphique.
     */
    public Graphique( int x, int y, String nom) {
        this.x = x;
        this.y = y;
        this.nom = nom ;
    }


     /**
     * Méthode abstraite pour dessiner le graphique.
     * @param g Contexte graphique utilisé pour le dessin.
     */  
    public abstract void dessiner(Graphics g);

      /**
     * Méthode abstraite pour déterminer si un point spécifique est contenu dans le graphique.
     * @param x Coordonnée x du point à vérifier.
     * @param y Coordonnée y du point à vérifier.
     * @return true si le point est contenu dans le graphique, false sinon.
     */
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
