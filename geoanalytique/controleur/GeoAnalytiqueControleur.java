package geoanalytique.controleur;

import geoanalytique.model.*;
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
                if (coords.length >= 4) {  
                    double x2 = Double.parseDouble(coords[2].trim());
                    double y2 = Double.parseDouble(coords[3].trim());
                    Segment segment = new Segment(new Point(x1, y1, "Point1"), new Point(x2, y2, "Point2"), "Segment");
                    geoObjects.add(segment);
                    System.out.println("Segment ajouté : " + segment);
                } else {
                    System.out.println("Pas assez de coordonnées pour créer un segment");
                }
                break;
        }
    
        notifyObservers(); 
    }
    
    
    

public void notifyObservers() {
    view.repaint();  
}

    // Sélectionne un objet du système et informe la vue des opérations possibles
    public void selectionner(GeoObject objet) {
        selectedObject = objet;
        // Peut-être mettre à jour la vue pour afficher des options ou des détails sur l'objet sélectionné
        // showObjectDetails(selectedObject);
        // highlightSelectedObject(selectedObject);
    }

    // Désélectionne l'objet actuellement sélectionné et informe la vue
    public void deselectionner() {
        selectedObject = null;
        // Peut-être mettre à jour la vue pour retirer les détails ou les surbrillances de l'objet précédemment sélectionné
        // hideObjectDetails();
        // unhighlightSelectedObject();
    }
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
            }
            // Ajoutez ici d'autres cas pour d'autres types géométriques spécifiques
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
        // Vous devrez peut-être ajuster le rayon si l'échelle du ViewPort a changé
    }
    
    private void recalculerCercle(Cercle cercle) {
        recalculerPoint(cercle.getCentre());
        // Ajuster le rayon si nécessaire
    }
    
    private void recalculerSegment(Segment segment) {
        if (segment.getPointA() != null && segment.getPointB() != null) {
            recalculerPoint(segment.getPointA());
            recalculerPoint(segment.getPointB());
        } else {
            System.out.println("Un ou plusieurs points du segment sont nuls.");//debug
        }
    }
    

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