package geoanalytique.controleur;


import geoanalytique.exception.ArgumentOperationException;
import geoanalytique.model.*;
import geoanalytique.model.geoobject.operation.*;
import geoanalytique.view.GeoAnalytiqueView;


/**
 * Contrôle les opérations spécifiques qui peuvent être effectuées sur les objets géométriques.
 */

public class OperationControleur {
    private GeoAnalytiqueControleur geoAnalytiqueControleur;
    private GeoAnalytiqueView view;

    /**
     * Constructeur pour OperationControleur.
     *
     * @param geoControleur Le contrôleur principal de GeoAnalytique.
     * @param view La vue associée à ce contrôleur.
     */

    public OperationControleur(GeoAnalytiqueControleur geoControleur, GeoAnalytiqueView view) {
        this.geoAnalytiqueControleur = geoControleur;
        this.view = view;
    }


    /**
     * Exécute une opération spécifiée sur les objets géométriques.
     *
     * @param type Le type d'opération à exécuter.
     * @param nom Le nom de l'objet géométrique sur lequel l'opération doit être exécutée.
     * @param coordsTextfield Les coordonnées ou autres arguments nécessaires pour l'opération.
     * @return Un message indiquant le résultat de l'opération.
     */
    public String effectuerOperation(String type, String nom, String coordsTextfield) {
        String info ="" ;
        switch (type) {
            case "deplacer un point":// Verifier si les coordonnees sont fournies correctement
        if (coordsTextfield == null || coordsTextfield.split(",").length < 2) {
            info = "Erreur : Coordonnees invalides ou incomplètes.";
        }
    
        String[] coords = coordsTextfield.split(",");
        double newX, newY;
        
            newX = Double.parseDouble(coords[0].trim());
            newY = Double.parseDouble(coords[1].trim());
                DeplacerPointOperation operation = new DeplacerPointOperation();
                Point pointToMove = geoAnalytiqueControleur.geoObjects.stream()
                    .filter(g -> g instanceof Point && g.getNom().equals(nom))
                    .map(g -> (Point) g)
                    .findFirst()
                    .orElse(null);
    
                if (pointToMove == null) {
                    info = "Erreur : Aucun point trouve avec le nom specifie.";
                }
    
                // Configurer et executer l'operation
                operation.setArgument(1, pointToMove);
                operation.setArgument(2, newX);
                operation.setArgument(3, newY);
                Point result = (Point) operation.calculate();
    
                // Mettre à jour les coordonnees du point
                if (result != null) {
                    pointToMove.setAbscisse(newX);
                    pointToMove.setOrdonnee(newY);
                    pointToMove.setNom(nom);
                    view.repaint();
                    info = "Le point a ete deplace avec succès.";
                } else {
                    info = "Erreur lors du deplacement du point.";
                }
                break ;
                case "changer nom":
            // Trouver l'objet géométrique avec le nom donné
            GeoObject geoObject = this.geoAnalytiqueControleur.geoObjects.stream()
                .filter(g -> g.getNom().equals(nom))
                .findFirst()
                .orElse(null);
            
            if (geoObject == null) {
                info = "Erreur : Aucun objet trouvé avec le nom spécifié.";
            } else {
                ChangeNomOperation op = new ChangeNomOperation(geoObject, coordsTextfield.toString());
              
                    try {
                        op.setArgument(1, coordsTextfield);
                    } catch (ArgumentOperationException e) {
                        e.printStackTrace();
                    }
                    GeoObject updatedObject = (GeoObject) op.calculate();
                    info = "Nom changé avec succès de '" + nom + "' à '" + updatedObject.getNom() + "'.";
            }
            break;
            default:
                info = "Erreur : Operation non reconnue.";
        }

        view.repaint();
        return info;
    }
    
    
}
