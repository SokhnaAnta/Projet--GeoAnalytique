package geoanalytique.util;

import geoanalytique.graphique.*;
import geoanalytique.model.*;

public class Dessinateur implements GeoObjectVisitor<Graphique> {
    private ViewPort viewPort;

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    public Dessinateur(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

    @Override
    public Graphique visit(Point point) {
        int x = viewPort.convertX(point.getAbscisse());
        int y = viewPort.convertY(point.getOrdonnee());
        System.out.println("Point " + point.getNom() + " dessiné à x=" + x + " y=" + y);
        return new GCoordonnee(x, y, point.getNom());
    }

    @Override
    
    public Graphique visit(Droite droite) {
        int x1 = viewPort.convertX(droite.getPointA().getAbscisse());
        int y1 = viewPort.convertY(droite.getPointA().getOrdonnee());
        int x2 = viewPort.convertX(droite.getPointB().getAbscisse());
        int y2 = viewPort.convertY(droite.getPointB().getOrdonnee());
        System.out.println("droite dessine a "+x1+" "+y1+" "+x2+" "+y2);
        return new GLigne(x1, y1, x2, y2,droite.getNom());
    }

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
    

    @Override
    public Graphique visit(Cercle cercle) {
        int xCentre = viewPort.convertX(cercle.getCentre().getAbscisse());
        int yCentre = viewPort.convertY(cercle.getCentre().getOrdonnee());
        int rayon = (int) (cercle.getRayonX() * viewPort.getEchelle()); // rayonX et rayonY sont égaux
    
        int x = xCentre - rayon;
        int y = yCentre - rayon;
    
        return new GOvale(x, y, rayon * 2, rayon * 2, cercle.getNom());
    }
    
 
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



public Graphique visit(Triangle triangle) {
    int[] xPoints = {viewPort.convertX(triangle.getPointA().getAbscisse()), viewPort.convertX(triangle.getPointB().getAbscisse()), viewPort.convertX(triangle.getPointC().getAbscisse())};
    int[] yPoints = {viewPort.convertY(triangle.getPointA().getOrdonnee()), viewPort.convertY(triangle.getPointB().getOrdonnee()), viewPort.convertY(triangle.getPointC().getOrdonnee())};
    return new GPolygone(xPoints[1], yPoints[2], xPoints, yPoints, 3,triangle.getNom()); 
}



}
