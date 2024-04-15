package geoanalytique.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.view.GeoAnalytiqueView;

public class GeoAnalytiqueGUI extends JFrame {
    private GeoAnalytiqueControleur controleur;
    private GeoAnalytiqueView vue;

    public GeoAnalytiqueGUI(GeoAnalytiqueControleur controleur, GeoAnalytiqueView vue) {
        super("GeoAnalytique - Dessinez vos formes geometriques");
        this.controleur = controleur;
        this.vue = vue;

        initializeUI();
        setupWindowListener();
    }

    private void setupWindowListener() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateViewport();

                //controleur.recalculerPoints();
            }
        });
    }

    private void updateViewport() {
        int width = this.getContentPane().getWidth();
        int height = this.getContentPane().getHeight();
    
        // Mise à jour des dimensions du ViewPort
        vue.getViewPort().setLargeur(width);
        vue.getViewPort().setLongueur(height);
    
        // Recalculer les limites pour maintenir le centre à (0,0)
        double echelle = vue.getViewPort().getEchelle();
        vue.getViewPort().setxMin(-width / 2 / echelle);
        vue.getViewPort().setxMax(width / 2 / echelle);
        vue.getViewPort().setyMin(-height / 2 / echelle);
        vue.getViewPort().setyMax(height / 2 / echelle);
    
        // Redessiner la vue pour refléter les changements
        vue.repaint();
    }
    
    private void initializeUI() {
        String[] shapes = {"Point", "Droite", "Segment", "Ellipse", "Cercle", "Parallelogramme", "Rectangle","Carre","Triangle"};
        JComboBox<String> shapeSelector = new JComboBox<>(shapes);
        JTextField coordinatesField = new JTextField(20);
        JButton drawButton = new JButton("Dessiner");

        JPanel drawingPanel = vue;
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Sélectionnez une forme:"));
        controlPanel.add(shapeSelector);
        controlPanel.add(new JLabel("Entrez les coordonnees:"));
        controlPanel.add(coordinatesField);
        controlPanel.add(drawButton);

        drawButton.addActionListener(e -> drawShape(shapeSelector.getSelectedItem().toString(), coordinatesField.getText()));

        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(drawingPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1500, 1500));  // Définir une taille initiale
        this.setLocationRelativeTo(null);
    }

    private void drawShape(String shape, String coordinatesText) {
        controleur.addObjet(shape, coordinatesText);
    }
}
