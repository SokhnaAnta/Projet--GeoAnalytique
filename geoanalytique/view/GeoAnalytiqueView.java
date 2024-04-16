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

/**
 * Classe représentant la vue graphique dans l'application GeoAnalytique.
 * Elle est responsable de l'affichage des objets graphiques et de la gestion des interactions de la souris.
 */
public class GeoAnalytiqueView extends JPanel {
    private GeoAnalytiqueControleur controleur;
    private Dessinateur dessinateur;
    private ViewPort viewPort;
    private List<Graphique> graphiques = new ArrayList<>();

    /**
     * Constructeur de GeoAnalytiqueView.
     * @param controleur Le contrôleur qui gère la logique de l'application.
     * @param viewPort Le viewport qui gère les transformations des coordonnées.
     */
    public GeoAnalytiqueView(GeoAnalytiqueControleur controleur, ViewPort viewPort) {
        this.setBackground(Color.WHITE);  // Définit la couleur de fond du panneau.
        this.controleur = controleur;
        this.dessinateur = new Dessinateur(viewPort);
        this.viewPort = viewPort;
        setToolTipText(null);  // Initialement, pas de texte d'info-bulle
        initMouseListeners();  // Initialise les écouteurs de souris pour la gestion du survol.
    }

    /**
     * Initialise les écouteurs de la souris pour détecter les mouvements et afficher les info-bulles.
     */
    private void initMouseListeners() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                handleMouseHover(e.getX(), e.getY());
            }
        });
    }

    /**
     * Gère le survol de la souris pour afficher les noms des objets graphiques sous le curseur.
     * @param x La position x de la souris dans le panneau.
     * @param y La position y de la souris dans le panneau.
     */
    private void handleMouseHover(int x, int y) {
        for (Graphique graphique : graphiques) {
            if (graphique.contientPoint(x, y)) {
                setToolTipText(graphique.getNom());
                return;
            }
        }
        setToolTipText(null);
    }

    /**
     * Surcharge de la méthode paintComponent pour dessiner les objets graphiques.
     * @param g L'objet Graphics utilisé pour le dessin.
     */
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

    // Getters et setters pour les propriétés de la vue.
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
