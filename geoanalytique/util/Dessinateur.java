package geoanalytique.util;

import geoanalytique.graphique.*;
import geoanalytique.model.*;


/**
 * Classe capable de renvoyer les objets géométriques en leurs représentations graphiques.
 */

public class Dessinateur implements GeoObjectVisitor<Graphique> {
    private ViewPort viewPort;  // Le viewport pour la conversion des coordonnées


    /**
     * Constructeur avec un ViewPort.
     * @param viewPort Le ViewPort utilisé pour les conversions de coordonnées.
     */
    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    public Dessinateur(ViewPort viewPort) {
        this.viewPort = viewPort;
    }


    /**
     * Visite un Point et le convertit en un objet graphique GCoordonnee.
     */

    public Graphique visit(Point point) {
        int x = viewPort.convertX(point.getAbscisse());
        int y = viewPort.convertY(point.getOrdonnee());
        return new GCoordonnee(x, y, point.getNom());
    }


    /**
     * Visite une Droite et la convertit en un objet graphique GLigne.
     */
    
    @Override
    public Graphique visit(Droite droite) {
        if (droite instanceof Segment) {
            int x1 = viewPort.convertX(droite.getPointA().getAbscisse());
            int y1 = viewPort.convertY(droite.getPointA().getOrdonnee());
            int x2 = viewPort.convertX(droite.getPointB().getAbscisse());
            int y2 = viewPort.convertY(droite.getPointB().getOrdonnee());
            return new GLigne(x1, y1, x2, y2, droite.getNom());
        } else {
            int x1 = viewPort.convertX(droite.getPointA().getAbscisse());
            int y1 = viewPort.convertY(droite.getPointA().getOrdonnee());
            int x2 = viewPort.convertX(droite.getPointB().getAbscisse());
            int y2 = viewPort.convertY(droite.getPointB().getOrdonnee());

            // Dessiner la droite prolongée jusqu'aux bords de la zone de dessin
            int width = viewPort.getLargeur(); // Largeur de la zone de dessin
            int height = viewPort.getLongueur(); // Hauteur de la zone de dessin

            // Calculer les coordonnées du point de prolongement
            int extendedX1, extendedY1, extendedX2, extendedY2;

            // Calculer les coordonnées du point de prolongement
            if (x2 != x1) { // Éviter la division par zéro
                // Pente de la droite
                double slope = (double) (y2 - y1) / (x2 - x1);

                // Prolonger jusqu'au bord gauche
                extendedX1 = 0;
                extendedY1 = (int) (y1 - slope * (x1 - extendedX1));

                // Prolonger jusqu'au bord droit
                extendedX2 = width;
                extendedY2 = (int) (y2 + slope * (extendedX2 - x2));

                // Dessiner la portion prolongée de la droite
                return new GLigne(extendedX1, extendedY1, extendedX2, extendedY2, droite.getNom());
            } else {
                // Si la droite est verticale, prolongez-la simplement jusqu'au haut et en bas
                // de la zone de dessin
                return new GLigne(x1, 0, x1, height, droite.getNom());
            }
        }
    }


     /**
     * Visite une Ellipse et la convertit en un objet graphique GOvale.
     */
    @Override
    public Graphique visit(Ellipse ellipse) {
        int xCentre = viewPort.convertX(ellipse.getCentre().getAbscisse());
        int yCentre = viewPort.convertY(ellipse.getCentre().getOrdonnee());
    
        int largeur = (int) (2 * ellipse.getRayonX() * viewPort.getEchelle());
        int longueur = (int) (2 * ellipse.getRayonY() * viewPort.getEchelle());
    
        int x = xCentre - largeur / 2;
        int y = yCentre - longueur / 2;
    
        return new GOvale(x, y, largeur, longueur, ellipse.getNom());
    }
    
     /**
     * Visite un Cercle et le convertit en un objet graphique GOvale.
     */

    @Override
    public Graphique visit(Cercle cercle) {
        int xCentre = viewPort.convertX(cercle.getCentre().getAbscisse());
        int yCentre = viewPort.convertY(cercle.getCentre().getOrdonnee());
        int rayon = (int) (cercle.getRayonX() * viewPort.getEchelle()); // rayonX et rayonY sont égaux
    
        int x = xCentre - rayon;
        int y = yCentre - rayon;
        return new GOvale(x, y, rayon * 2, rayon * 2, cercle.getNom());
    }
    

    /**
     * Visite un Parallelogramme et le convertit en un objet graphique GPolygone.
     */
 
@Override
public Graphique visit(Parallelogramme parallelogramme) {
    int[] xPoints = new int[] {
        viewPort.convertX(parallelogramme.getPointA().getAbscisse()),
        viewPort.convertX(parallelogramme.getPointB().getAbscisse()),
        viewPort.convertX(parallelogramme.getPointC().getAbscisse()),
        viewPort.convertX(parallelogramme.getPointD().getAbscisse())
    };
    int[] yPoints = new int[] {
        viewPort.convertY(parallelogramme.getPointA().getOrdonnee()),
        viewPort.convertY(parallelogramme.getPointB().getOrdonnee()),
        viewPort.convertY(parallelogramme.getPointC().getOrdonnee()),
        viewPort.convertY(parallelogramme.getPointD().getOrdonnee())
    };
    return new GPolygone(xPoints[1], yPoints[2],xPoints, yPoints, 4, parallelogramme.getNom());
}


     /**
     * Visite un Triangle et le convertit en un objet graphique GPolygone.
     */
    public Graphique visit(Triangle triangle) {
        int[] xPoints = {viewPort.convertX(triangle.getPointA().getAbscisse()), viewPort.convertX(triangle.getPointB().getAbscisse()), viewPort.convertX(triangle.getPointC().getAbscisse())};
        int[] yPoints = {viewPort.convertY(triangle.getPointA().getOrdonnee()), viewPort.convertY(triangle.getPointB().getOrdonnee()), viewPort.convertY(triangle.getPointC().getOrdonnee())};
        return new GPolygone(xPoints[1], yPoints[2], xPoints, yPoints, 3,triangle.getNom()); 
    }


   /**
     * Visite un Texte et le convertit en un objet graphique GTexte.
     */
@Override
public Graphique visit(Texte texte) {

    int x = viewPort.convertX(texte.getX());
    int y = viewPort.convertY(texte.getY());
    String  contenu = texte.getContenu();
    return new GTexte(x, y, contenu, "") ;

    
}





}
