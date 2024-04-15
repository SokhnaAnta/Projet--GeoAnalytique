package geoanalytique.controleur;

import geoanalytique.model.*;
import geoanalytique.model.geoobject.operation.DistancePoint;
import geoanalytique.util.Dessinateur;

import java.util.List;
import java.util.ArrayList;
import geoanalytique.view.GeoAnalytiqueView;

public class GeoAnalytiqueControleur {
    private List<GeoObject> geoObjects;
    private GeoObject selectedObject;
    private ViewPort viewPort ;
    public ViewPort getViewPort() {
        return viewPort;
    }


    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }


    public GeoObject getSelectedObject() {
        return selectedObject;
    }


    public void setSelectedObject(GeoObject selectedObject) {
        this.selectedObject = selectedObject;
    }

    private GeoAnalytiqueView view; 

    public GeoAnalytiqueControleur(GeoAnalytiqueView view, ViewPort viewPort) {
        this.geoObjects = new ArrayList<>();
        this.view = view;
        this.viewPort = viewPort ;
        this.view.setDessinateur(new Dessinateur(this.viewPort));
        this.selectedObject = null;
        initDefaultObjects();
    }
    

    public void addObjet(String type, String coordText) {
        String[] coords = coordText.split(",");
        double x1 = Double.parseDouble(coords[0].trim());
        double y1 = Double.parseDouble(coords[1].trim());
        
        switch (type) {
                case "Point":
                Point point = new Point(x1, y1, "Nom du Point");
                geoObjects.add(point);
                System.out.println("Point ajouté : " + point);
                break;
                case "Droite":
                if (coords.length >= 4) {  
                    double x2 = Double.parseDouble(coords[2].trim());
                    double y2 = Double.parseDouble(coords[3].trim());
                    Droite droite = new Droite(new Point(x1, y1, "Point1"), new Point(x2, y2, "Point2"), "Droite");
                    geoObjects.add(droite);
                    System.out.println("Droite ajoutée : " + droite);
                } else {
                    System.out.println("Pas assez de coordonnées pour créer une droite");
                }
                break;
                case "Segment":
                if (coords.length == 4) {  
                    double x2 = Double.parseDouble(coords[2].trim());
                    double y2 = Double.parseDouble(coords[3].trim());
                    System.out.println("user a taper segment "+x1+" "+y1+" "+x2+" "+y2);
                    Segment segment = new Segment(new Point(x1, y1, "Point1"), new Point(x2, y2, "Point2"), "Segment");
                    geoObjects.add(segment);
                    System.out.println("Segment ajouté : " + segment);
                } else {
                    System.out.println("Pas assez de coordonnées pour créer un segment");
                }
                break;
                case "Ellipse":
                  // x,y,ry,ry
                    double rx = Double.parseDouble(coords[2].trim());  // Rayon sur axe X
                    double ry = Double.parseDouble(coords[3].trim());  // Rayon sur axe Y
                    Ellipse ellipse = new Ellipse(new Point(x1, y1, "Centre de l'Ellipse"), rx, ry, "Ellipse");
                    geoObjects.add(ellipse);
                    System.out.println("Ellipse ajoutée : " + ellipse);
                break; 

            case "Cercle":
            if (coords.length == 3) {
                //x,y,rayon
                double rayon = Double.parseDouble(coords[2].trim());
                Cercle cercle = new Cercle(new Point(x1, y1,"centre"), rayon, "Cercle");
                geoObjects.add(cercle);
                System.out.println("Cercle ajouté : " + cercle);
            } else {
                System.out.println("Pas assez de coordonnées pour créer un cercle");
            }
            break;   

            case "Parallelogramme":
            //x1,y1,x2,y2,x3,y3,x4,y4
            if (coords.length == 8) {
                double x2 = Double.parseDouble(coords[2].trim());
                double y2 = Double.parseDouble(coords[3].trim());
                double x3 = Double.parseDouble(coords[4].trim());
                double y3 = Double.parseDouble(coords[5].trim());
                double x4 = Double.parseDouble(coords[6].trim());
                double y4 = Double.parseDouble(coords[7].trim());
                Parallelogramme parallelogramme = new Parallelogramme(
                    new Point(x1, y1,"nom"), new Point(x2, y2,""), new Point(x3, y3,""), new Point(x4, y4,""), "Parallelogramme"
                );
                geoObjects.add(parallelogramme);
                System.out.println("Parallelogramme ajouté : " + parallelogramme);
            } else {
                System.out.println("Pas assez de coordonnées pour créer un parallelogramme");
            }
            break;
        case "Triangle":
            if (coords.length == 6) {
               //x1,y1,x2,y2,x3,y3
                double x2 = Double.parseDouble(coords[2].trim());
                double y2 = Double.parseDouble(coords[3].trim());
                double x3 = Double.parseDouble(coords[4].trim());
                double y3 = Double.parseDouble(coords[5].trim());
                Triangle triangle = new Triangle(
                    new Point(x1, y1,"p1"), new Point(x2, y2,"p2"), new Point(x3, y3,"p3"), "Triangle"
                );
                geoObjects.add(triangle);
                System.out.println("Triangle ajouté : " + triangle);
            } else {
                System.out.println("Pas assez de coordonnées pour créer un triangle");
            }
            break;
            case "Rectangle":
            if (coords.length == 4) {
                //x1,y1,x2,y2
                double x2 = Double.parseDouble(coords[2].trim());
                double y2 = Double.parseDouble(coords[3].trim());
                Rectangle rectangle = new Rectangle(
                    new Point(x1, y1,""), // Point A (haut gauche)
                    new Point(x2, y1,""), // Point B (haut droit)
                    new Point(x2, y2,""), // Point C (bas droit)
                    new Point(x1, y2,""), // Point D (bas gauche)
                    "Rectangle"
                );
                geoObjects.add(rectangle);
                System.out.println("Rectangle ajouté : " + rectangle);
            } else {
                System.out.println("Pas assez de coordonnées pour créer un rectangle");
            }
            break;

            case "Carre":
            if (coords.length == 3) {
                //x1,y1,longueurcote
                double longueurcote = Double.parseDouble(coords[2].trim());
                Point pointA = new Point(x1, y1,""); // haut gauche
                Point pointB = new Point(x1 + longueurcote, y1,""); // haut droit 
                Point pointC = new Point(x1 + longueurcote, y1 + longueurcote,""); // bas droite c
                Point pointD = new Point(x1, y1 + longueurcote,""); // bas gauche 
                DistancePoint dAB = new DistancePoint(pointA, pointB);
                DistancePoint dBC = new DistancePoint(pointB, pointC);
                DistancePoint dCD = new DistancePoint(pointC, pointD);
                DistancePoint dDA = new DistancePoint(pointD, pointA);

                if (dAB.getDistance() == dBC.getDistance() && dBC.getDistance() == dCD.getDistance() && dCD.getDistance() == dDA.getDistance()) {
                    Carre carre = new Carre(pointA, pointB, pointC, pointD, "Carre");
                    geoObjects.add(carre);
                    System.out.println("Carré ajouté : " + carre);
                }
                } else {
                    System.out.println("Les points fournis ne forment pas un carré valide");
                }
            
            break;
    }


    
        notifyObservers();  // rafraîchir l'affichage
    }
    
    
    

public void notifyObservers() {
    view.repaint();  
}

    // Sélectionne un objet du système et informe la vue des opérations possibles
    public void selectionner(GeoObject objet) {
        selectedObject = objet;
    
        
    }

    public void deselectionner() {
        selectedObject = null;
        
    }
    /* 
    public void recalculerPoints() {
        for (GeoObject objet : geoObjects) {
            if (objet instanceof Point) {
                recalculerPoint((Point) objet);
            } else if (objet instanceof Segment) {
                recalculerSegment((Segment) objet);
            } else if (objet instanceof Droite) {
                recalculerDroite((Droite) objet);
            } else if (objet instanceof Ellipse) {
                recalculerEllipse((Ellipse) objet);
            } else if (objet instanceof Cercle) {
                recalculerCercle((Cercle) objet);
            } else if (objet instanceof Parallelogramme) {
                recalculerCercle((Cercle) objet);
            }

        } 
    
        // Notification pour rafraîchir l'affichage
        notifyObservers();
    }
    
    private void recalculerPoint(Point point) {
        point.setAbscisse(viewPort.convertXInverse(viewPort.convertX(point.getAbscisse())));
        point.setOrdonnee(viewPort.convertYInverse(viewPort.convertY(point.getOrdonnee())));
    }
    
    private void recalculerDroite(Droite droite) {
        recalculerPoint(droite.getPointA());
        recalculerPoint(droite.getPointB());
    }
    
    private void recalculerEllipse(Ellipse ellipse) {
        recalculerPoint(ellipse.getCentre());
    }
    
    private void recalculerCercle(Cercle cercle) {
        recalculerPoint(cercle.getCentre());
    }
    
    private void recalculerSegment(Segment segment) {
        if (segment.getPointA() != null && segment.getPointB() != null) {
            recalculerPoint(segment.getPointA());
            recalculerPoint(segment.getPointB());
        } else {
            System.out.println("Un ou plusieurs points du segment sont nuls.");//debug
        }
    }
    
*/
    public List<GeoObject> getGeoObjects() {
        return this.geoObjects;
    }
    
    private void initDefaultObjects() {
        // Initialisation des axes
        Droite axeOx = new Droite(new Point(viewPort.getxMin(), 0, "Axe Ox Min"), new Point(viewPort.getxMax(), 0, "Axe Ox Max"), "Axe Ox");
        Droite axeOy = new Droite(new Point(0, viewPort.getyMin(), "Axe Oy Min"), new Point(0, viewPort.getyMax(), "Axe Oy Max"), "Axe Oy");
        geoObjects.add(axeOx);
        geoObjects.add(axeOy);
    
        // Ajouter des graduations sur les axes
    }
    
    
    
    
}