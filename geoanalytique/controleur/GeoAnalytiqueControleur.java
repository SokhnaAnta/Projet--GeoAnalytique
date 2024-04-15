package geoanalytique.controleur;

import geoanalytique.model.*;
import geoanalytique.model.geoobject.operation.DistancePoint;

import java.util.List;


import java.util.ArrayList;
import geoanalytique.view.GeoAnalytiqueView;

public class GeoAnalytiqueControleur {
    private List<GeoObject> geoObjects;
    private GeoObject selectedObject;
    private ViewPort viewPort ;
    private GeoAnalytiqueView view; 

    public List<GeoObject> getGeoObjects() {
        return this.geoObjects;
    }
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

    

    public GeoAnalytiqueControleur(GeoAnalytiqueView view, ViewPort viewPort) {
        this.geoObjects = new ArrayList<>();
        this.view = view;
        this.viewPort = viewPort ;
        this.selectedObject = null;
    }
    

    public String addObjet(String type, String coordText,String nom) {
        if(nom.equals(null)){
         nom="geoObjet" ;
        }
        String[] coords = coordText.split(",");
        double x1 = Double.parseDouble(coords[0].trim());
        double y1 = Double.parseDouble(coords[1].trim());
        String message ="Votre "+type+" "+nom+" a bien ete dessine" ;
        
        switch (type) {
                case "Point":
                Point point = new Point(x1, y1, "Nom du Point");
                geoObjects.add(point);
                break;
                case "Droite":
                if (coords.length == 4) {  
                    double x2 = Double.parseDouble(coords[2].trim());
                    double y2 = Double.parseDouble(coords[3].trim());
                    Droite droite = new Droite(new Point(x1, y1, "Point1"), new Point(x2, y2, "Point2"), nom);
                    geoObjects.add(droite);
                } else {
                    message = "Pas assez de coordonnées pour dessiner une droite";
                }
                break;
                case "Segment":
                if (coords.length == 4) {  
                    double x2 = Double.parseDouble(coords[2].trim());
                    double y2 = Double.parseDouble(coords[3].trim());
                    Segment segment = new Segment(new Point(x1, y1, "Point1"), new Point(x2, y2, "Point2"), nom);
                    geoObjects.add(segment);
                } else {
                    message =  "Pas assez de coordonnées pour dessiner un segment" ;
                }
                break;
                case "Ellipse":
                  // x,y,ry,ry
                  if (coords.length == 4) {
                    double rx = Double.parseDouble(coords[2].trim());  // Rayon sur axe X
                    double ry = Double.parseDouble(coords[3].trim());  // Rayon sur axe Y
                    Ellipse ellipse = new Ellipse(new Point(x1, y1, "Centre de l'Ellipse"), rx, ry, nom);
                    geoObjects.add(ellipse);
                } else {
                    message = "Pas assez de coordonnées pour dessiner une ellipse";
                }
                break; 

            case "Cercle":
            if (coords.length == 3) {
                //x,y,rayon
                double rayon = Double.parseDouble(coords[2].trim());
                Cercle cercle = new Cercle(new Point(x1, y1,"centre"), rayon, nom);
                geoObjects.add(cercle);
            } else {
                message = "Pas assez de coordonnées pour dessiner un cercle";
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
                    new Point(x1, y1,"nom"), new Point(x2, y2,""), new Point(x3, y3,""), new Point(x4, y4,""), nom
                );
                geoObjects.add(parallelogramme);
            } else {
                message = "Pas assez de coordonnées pour dessiner un parallelogramme";
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
                    new Point(x1, y1,"p1"), new Point(x2, y2,"p2"), new Point(x3, y3,"p3"),nom);
                geoObjects.add(triangle);
            } else {
                message = "Pas assez de coordonnées pour dessiner un triangle";
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
                    nom
                );
                geoObjects.add(rectangle);
            } else {
                message =  "Pas assez de coordonnées pour dessiner un rectangle";
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
                    Carre carre = new Carre(pointA, pointB, pointC, pointD, nom);
                    geoObjects.add(carre);
                }
                } else {
                    message = "Les points fournis ne forment pas un carré valide";
                }
            
            break;
    }

       view.repaint(); 
       return message ;
    }
    



  

   
    
    public void recalculPoint() {
        // Initialisation des axes
        Droite axeOx = new Droite(new Point(viewPort.getxMin(), 0, "Axe Ox Min"), new Point(viewPort.getxMax(), 0, "Axe Ox Max"), "Axe Ox");
        Droite axeOy = new Droite(new Point(0, viewPort.getyMin(), "Axe Oy Min"), new Point(0, viewPort.getyMax(), "Axe Oy Max"), "Axe Oy");
        geoObjects.add(axeOx);
        geoObjects.add(axeOy);

        // Recalcul
        int largeur = view.getWidth() ;
        int longueur = view.getHeight();
    
        // Mise à jour des dimensions du ViewPort
        view.getViewPort().setLargeur(largeur);
        view.getViewPort().setLongueur(longueur);
    
        // Recalculer les limites pour maintenir le centre à (0,0)
        double echelle = view.getViewPort().getEchelle();
        view.getViewPort().setxMin(-largeur / 2 / echelle);
        view.getViewPort().setxMax(largeur / 2 / echelle);
        view.getViewPort().setyMin(-longueur / 2 / echelle);
        view.getViewPort().setyMax(longueur / 2 / echelle);
    
        // Redessiner la vue pour refléter les changements
        view.repaint();
        addGraduations();
    }
    public void addGraduations() {
        int largeur = viewPort.getLargeur();
        int longueur = viewPort.getLongueur(); 
        String axeXnom= "Axe Ox Graduation ";
        String axeYnom = "Axe Oy Graduation ";
    // Supprimer les anciennes graduations si nécessaire
    geoObjects.removeIf(g -> g.getNom().contains("Graduation"));
        // Graduations sur l'axe X
        for (int i = -largeur; i <= largeur; i++) {
            if (i != 0) {  // Éviter de placer une graduation à l'origine
                geoObjects.add(new Point(i, 0, axeXnom+ i));
            }
        }
    
        // Graduations sur l'axe Y
        for (int i = -longueur; i <= longueur; i++) {
            if (i != 0) {  // Éviter de placer une graduation à l'origine
                geoObjects.add(new Point(0, i, axeYnom + i));
            }
        }
    }
    

    public String updateInfo(String shape) {
        String message=" " ;
        switch (shape) {
            case "Point":
                message = "Format: x,y (ex: 1,-3.5)";
                break ;
            case "Droite":
                message = "Format: x1,y1,x2,y2 (ex: 1,-3,2,-2)";
                break ;
            case "Segment":
                message = "Format: x1,y1,x2,y2 (ex: 1,-3,2,2-7)";
                break ;
            case "Ellipse":
                message = "Format: x,y,rayonX,rayonY (ex: 1,1.5,5,3)";
                break ;
            case "Cercle":
                message = "Format: x,y,rayon (ex: 1,-5,-3)";
                break ;
            case "Parallelogramme":
                message = "Format: x1,y1,x2,y2,x3,y3,x4,y4 (ex: 1,3,2,-1,2,-2,5.3,2)";
                break ;
            case "Rectangle":
                message = "Format: x1,y1,x2,y2 (ex: 1,-1,-1,1)";
                break ;
            case "Carre":
                message = "Format: x1,y1,longueurCote (ex: 1,-4,3)";
                break ;
            case "Triangle":
                message = "Format: x1,y1,x2,y2,x3,y3 (ex: 1,150,150,2,50,200)";
                break ;
            default:
                message = "Sélectionnez une forme pour voir le format.";
                break ;
        }
        this.view.repaint();
        return message ;
    }

   
    
    public void selectionner(GeoObject objet) {
        
        this.selectedObject = objet;

        
    }

    public void deselectionner() {
       
    }
    
    
}