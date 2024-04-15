package geoanalytique.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import geoanalytique.model.*;
import geoanalytique.util.*;
import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.graphique.*;

public class GeoAnalytiqueView extends JPanel {
    private GeoAnalytiqueControleur controleur;
    private Dessinateur dessinateur ;
    private ViewPort viewPort; 

    public Dessinateur getDessinateur() {
        return dessinateur;
    }

    public void setDessinateur(Dessinateur dessinateur) {
        this.dessinateur = dessinateur;
    }

    private List<Graphique> graphiques = new ArrayList<>();

    public GeoAnalytiqueView(GeoAnalytiqueControleur controleur, ViewPort viewPort) {
        this.controleur = controleur;
        this.dessinateur = new Dessinateur(viewPort);
        this.viewPort = viewPort; 
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });
    }

    private void handleMouseClick(int x, int y) {
        for (Graphique graphique : graphiques) {
            if (graphique.contientPoint(x, y)) {
               //System.out.println("Objet sélectionné: " + graphique.getNom());
                return;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphiques.clear();
        

        for (GeoObject geoObject : controleur.getGeoObjects()) {
            Graphique graphique = geoObject.accept(this.dessinateur);
            graphiques.add(graphique);
            graphique.dessiner(g);
        }
    }

    public GeoAnalytiqueControleur getControleur() {
        return controleur;
    }

    public void setControleur(GeoAnalytiqueControleur controleur) {
        this.controleur = controleur;
    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

    public List<Graphique> getGraphiques() {
        return graphiques;
    }

    public void setGraphiques(List<Graphique> graphiques) {
        this.graphiques = graphiques;
    }

    
}
