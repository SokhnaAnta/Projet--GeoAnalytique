package geoanalytique;

import javax.swing.SwingUtilities;
import geoanalytique.gui.GeoAnalytiqueGUI;
import geoanalytique.model.ViewPort;
import geoanalytique.view.GeoAnalytiqueView;
import geoanalytique.controleur.GeoAnalytiqueControleur;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          
            ViewPort viewPort = new ViewPort(1000,1000,50);
            GeoAnalytiqueView view = new GeoAnalytiqueView(null, viewPort);  
            GeoAnalytiqueControleur controleur = new GeoAnalytiqueControleur(view, viewPort);

            view.setControleur(controleur);

            GeoAnalytiqueGUI gui = new GeoAnalytiqueGUI(controleur, view);
            gui.setVisible(true);
        });
    }
}
