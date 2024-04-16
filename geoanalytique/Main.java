package geoanalytique;

import javax.swing.SwingUtilities;
import geoanalytique.gui.GeoAnalytiqueGUI;
import geoanalytique.model.ViewPort;
import geoanalytique.view.GeoAnalytiqueView;
import geoanalytique.controleur.GeoAnalytiqueControleur;

/**
 * Classe principale du programme GeoAnalytique.
 */
public class Main {
    /**
     * Point d'entrée du programme.
     * @param args Les arguments de la ligne de commande (non utilisés dans ce programme).
     */
    public static void main(String[] args) {
        // Exécute l'initialisation de l'interface graphique dans le thread de l'Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(() -> {
            // Crée un viewport de taille 1000x1000 avec une échelle initiale de 50 pixels par unité.
            ViewPort viewPort = new ViewPort(1000, 1000, 50);
            
            // Crée une vue pour afficher les données géo-analytiques, initialisée avec une vue null et le viewport créé.
            GeoAnalytiqueView view = new GeoAnalytiqueView(null, viewPort);
            
            // Crée un contrôleur pour gérer les interactions utilisateur avec la vue et le viewport.
            GeoAnalytiqueControleur controleur = new GeoAnalytiqueControleur(view, viewPort);
            
            // Associe le contrôleur à la vue.
            view.setControleur(controleur);
            
            // Crée et affiche l'interface graphique utilisateur (GUI) pour interagir avec le programme.
            GeoAnalytiqueGUI gui = new GeoAnalytiqueGUI(controleur, view);
            gui.setVisible(true);
        });
    }
}
