package geoanalytique.controleur;
import geoanalytique.model.*;
import geoanalytique.model.geoobject.operation.DistancePoint;
import java.util.List;
import java.util.ArrayList;
import geoanalytique.view.GeoAnalytiqueView;

/**
 * Contrôleur pour gérer l'interaction entre la vue et le modèle dans GeoAnalytique.
 */

public class GeoAnalytiqueControleur {
    private List<GeoObject> geoObjects;   // Liste contenant tous les objets géométriques.
    private GeoObject selectedObject;     // Référence à l'objet géométrique actuellement sélectionné.
    private ViewPort viewPort;            // Gestionnaire de la vue et des transformations de coordonnées.
    private GeoAnalytiqueView view;       // La vue qui affiche les objets géométriques.

    /**
     * Constructeur pour initialiser le contrôleur avec la vue et le viewport.
     */

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


    /**
     * Ajoute un objet géométrique en fonction du type et des coordonnées fournies par l'utilisateur.
     * @param type Le type de l'objet géométrique à ajouter.
     * @param coordText Les coordonnées de l'objet sous forme de texte.
     * @param nom Le nom donné à l'objet géométrique.
     * @return Un message indiquant le résultat de l'opération.
     */

    public String addObjet(String type, String coordText,String nom) {
        if(nom.equals(null)){
         nom="geoObjet" ;  // Nom par défaut si aucun nom n'est fourni.
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
                    message = "Pas assez de coordonnees pour dessiner une droite";
                }
                break;
                case "Segment":
                if (coords.length == 4) {  
                    double x2 = Double.parseDouble(coords[2].trim());
                    double y2 = Double.parseDouble(coords[3].trim());
                    Segment segment = new Segment(new Point(x1, y1, "Point1"), new Point(x2, y2, "Point2"), nom);
                    geoObjects.add(segment);
                } else {
                    message =  "Pas assez de coordonnees pour dessiner un segment" ;
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
                    message = "Pas assez de coordonnees pour dessiner une ellipse";
                }
                break; 

            case "Cercle":
            if (coords.length == 3) {
                //x,y,rayon
                double rayon = Double.parseDouble(coords[2].trim());
                Cercle cercle = new Cercle(new Point(x1, y1,"centre"), rayon, nom);
                geoObjects.add(cercle);
            } else {
                message = "Pas assez de coordonnees pour dessiner un cercle";
            }
            break;   
             case "Parallelogramme":
            if (coords.length == 6) {

                double x2 = Double.parseDouble(coords[2].trim());
                double y2 = Double.parseDouble(coords[3].trim());
                double x3 = Double.parseDouble(coords[4].trim());
                double y3 = Double.parseDouble(coords[5].trim());
                // Vecteurs entre les points

                double vecBCX = x3 - x2;
                double vecBCY = y3 - y2;

                // Coordonnées du quatrième point
                double x4 = x1 + vecBCX;
                double y4 = y1 + vecBCY;

                Parallelogramme parallelogramme = new Parallelogramme(
                    new Point(x1, y1, "Point1"), new Point(x2, y2, "Point2"),
                    new Point(x3, y3, "Point3"), new Point(x4, y4, "Point4"), nom
                );
                                geoObjects.add(parallelogramme);
            } else {
                message = "Nombre incorrect de coordonnées pour dessiner un parallélogramme.";
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
                message = "Pas assez de coordonnees pour dessiner un triangle";
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
                message =  "Pas assez de coordonnees pour dessiner un rectangle";
            }
            break;

            case "Carre":
            if (coords.length == 4) { // On a besoin de 4 coordonnées pour former un carré avec 2 points
                // Récupération des coordonnées des deux points
        
                double x2 = Double.parseDouble(coords[2].trim());
                double y2 = Double.parseDouble(coords[3].trim());
        
                // Calcul des coordonnées des deux autres points pour former un carré
                double x3 = x2 + (y1 - y2);
                double y3 = y2 + (x2 - x1);
                double x4 = x1 + (y1 - y2);
                double y4 = y1 + (x2 - x1);
        
                // Création des objets Point
                Point pointA = new Point(x1, y1,"");
                Point pointB = new Point(x2, y2,"");
                Point pointC = new Point(x3, y3,"");
                Point pointD = new Point(x4, y4,"");
        
                // Calcul des distances entre les points pour vérifier si c'est un carré
                DistancePoint dAB = new DistancePoint(pointA, pointB);
                DistancePoint dBC = new DistancePoint(pointB, pointC);
                DistancePoint dCD = new DistancePoint(pointC, pointD);
                DistancePoint dDA = new DistancePoint(pointD, pointA);
        
                // Vérification si les côtés ont la même longueur
                if (dAB.getDistance() == dBC.getDistance() && 
                    dBC.getDistance() == dCD.getDistance() && 
                    dCD.getDistance() == dDA.getDistance()) {
                    Carre carre = new Carre(pointA, pointB, pointC, pointD, nom);
                    geoObjects.add(carre);
                } else {
                    message = "Les points fournis ne forment pas un carré valide";
                }
            } else {
                message = "Nombre de coordonnées incorrect pour former un carré";
            }
            break;
            default:
            message = "Erreur , merci de respecter le format";
            break;
    }

       view.repaint(); // Demande de rafraîchissement de la vue.
       return message ;
    }
    

    /**
     * Recalcule les points pour les axes et les graduations en fonction de la taille actuelle de la vue.
     */
    
    public void recalculPoint() {
        // Initialisation des axes
        Droite axeOx = new Droite(new Point(viewPort.getxMin(), 0, "Axe Ox Min"), new Point(viewPort.getxMax(), 0, "Axe Ox Max"), "Axe Ox");
        Droite axeOy = new Droite(new Point(0, viewPort.getyMin(), "Axe Oy Min"), new Point(0, viewPort.getyMax(), "Axe Oy Max"), "Axe Oy");
        geoObjects.add(axeOx);
        geoObjects.add(axeOy);

        // Recalcul des points
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
    
        // Redessiner la vue pour refleter les changements
        view.repaint();
        addGraduations();
    }


     /**
     * Ajoute des graduations aux axes.
     */

    public void addGraduations() {
        // Code pour ajouter des graduations ici.
        int largeur = viewPort.getLargeur();
        int longueur = viewPort.getLongueur(); 
        String axeXnom= "Axe Ox Graduation ";
        String axeYnom = "Axe Oy Graduation ";
    // Supprimer les anciennes graduations si necessaire
    geoObjects.removeIf(g -> g.getNom().contains("Graduation"));
        // Graduations sur l'axe X
        for (int i = -largeur; i <= largeur; i++) {
            if (i != 0) {  // eviter de placer une graduation à l'origine
                geoObjects.add(new Point(i, 0, axeXnom+ i));
            }
        }
    
        // Graduations sur l'axe Y
        for (int i = -longueur; i <= longueur; i++) {
            if (i != 0) {  // eviter de placer une graduation à l'origine
                geoObjects.add(new Point(0, i, axeYnom + i));
            }
        }
    }
    


     /**
     * Met à jour les informations affichées dans la GUI en fonction du type de forme sélectionnée.
     * @param forme La forme sélectionnée par l'utilisateur.
     * @return Le format de saisie attendu pour la forme.
     */
    public String updateInfo(String forme) {
         // Code pour retourner le format de saisie pour la forme sélectionnée.
        String message=" " ;
        switch (forme) {
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
                message = "Format: x,y,rayon (ex: 1,-5,3)";
                break ;
            case "Parallelogramme":
                message = "Format: x1,y1,x2,y2,x3,y3 (ex: 1,3,2,-1,2,-2)";
                break ;
            case "Rectangle":
                message = "Format: x1,y1,x2,y2 (ex: 1,-1,-1,1)";
                break ;
            case "Carre":
                message = "Format: x1,y1,x2,y2 (ex: 1,-4,3,6)";
                break ;
            case "Triangle":
                message = "Format: x1,y1,x2,y2,x3,y3 (ex: 1,1,-5,-2,5,2)";
                break ;
            default:
                message = "Selectionnez une forme pour voir le format.";
                break ;
        }
        this.view.repaint();
        return message ;
    }

    /**
     * Sélectionne un objet géométrique pour permettre des interactions spécifiques.
     * @param objet L'objet géométrique à sélectionner.
     */
    
    public void selectionner(GeoObject objet) {
        
        this.selectedObject = objet;

        
    }

    /**
     * Désélectionne l'objet géométrique actuellement sélectionné.
     */

    public void deselectionner() {
       
        this.selectedObject = null;
    }
    
    
}