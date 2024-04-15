package geoanalytique.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import geoanalytique.model.*;
import geoanalytique.util.*;
import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.graphique.*;

public class GeoAnalytiqueView extends JPanel {
    private GeoAnalytiqueControleur controleur;
    private Dessinateur dessinateur;
    private ViewPort viewPort;
    private List<Graphique> graphiques = new ArrayList<>();

    public GeoAnalytiqueView(GeoAnalytiqueControleur controleur, ViewPort viewPort) {
        this.setBackground(Color.WHITE);
        this.controleur = controleur;
        this.dessinateur = new Dessinateur(viewPort);
        this.viewPort = viewPort;
        setToolTipText(null);  // Initialement, pas de texte d'info-bulle
        initMouseListeners();
    }

    private void initMouseListeners() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                handleMouseHover(e.getX(), e.getY());
            }
        });
    }

    private void handleMouseHover(int x, int y) {
        for (Graphique graphique : graphiques) {
            if (graphique.contientPoint(x, y)) {
                setToolTipText(graphique.getNom()); 
                return; 
            }
        }
        setToolTipText(null);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphiques.clear();
        
        for (GeoObject geoObject : controleur.getGeoObjects()) {
            Graphique graphique = geoObject.accept(dessinateur);
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
