package geoanalytique.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;

import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.view.GeoAnalytiqueView;

public class GeoAnalytiqueGUI extends JFrame {
    private GeoAnalytiqueControleur controleur;
    private GeoAnalytiqueView vue;
    private JLabel infos;  
    public GeoAnalytiqueGUI(GeoAnalytiqueControleur controleur, GeoAnalytiqueView vue) {
        super("GeoAnalytique - Dessinez vos formes geometriques");
        this.controleur = controleur;
        this.vue = vue;

        initializeUI();
        setupWindowListener();
    }

    private void initializeUI() {
        String[] shapes = {"GeoObjet","Point", "Droite", "Segment", "Ellipse", "Cercle", "Parallelogramme", "Rectangle", "Carre", "Triangle"};
        JComboBox<String> shapeSelector = new JComboBox<>(shapes);
        JTextField coordinatesField = new JTextField(20);
        JButton drawButton = new JButton("Dessiner");
        infos = new JLabel("Selectionnez une forme pour voir le format.");
        JLabel erreur = new JLabel("") ;

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Selectionnez une forme:"));
        controlPanel.add(shapeSelector);
        controlPanel.add(new JLabel("Entrez les coordonnees:"));
        controlPanel.add(coordinatesField);
        controlPanel.add(drawButton);
        controlPanel.add(infos);
        controlPanel.add(erreur);

        shapeSelector.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                erreur.setText("");
                String selectedShape = (String) e.getItem().toString();
                infos.setText("Coordonnee : " + controleur.updateInfo(selectedShape));
            }
        });
        


        drawButton.addActionListener(e -> {
            erreur.setText(" : " +controleur.addObjet(shapeSelector.getSelectedItem().toString(), coordinatesField.getText()));
            
        });

        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(vue, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1500, 1500));
        this.setLocationRelativeTo(null);
    }

    private void setupWindowListener() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
               controleur.recalculPoint() ;

            }
        });
    }


  


}
