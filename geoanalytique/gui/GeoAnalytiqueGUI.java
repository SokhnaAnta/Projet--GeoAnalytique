
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
    private JTextField nameField; 

    public GeoAnalytiqueGUI(GeoAnalytiqueControleur controleur, GeoAnalytiqueView vue) {
        super("GeoAnalytique - Dessinez vos formes geometriques");
        this.controleur = controleur;
        this.vue = vue;
        initializeUI();
        setupWindowListener();
    }

    private void initializeUI() {
        String[] shapes = {"Point", "Droite", "Segment", "Ellipse", "Cercle", "Parallelogramme", "Rectangle", "Carre", "Triangle"};
        JComboBox<String> shapeSelector = new JComboBox<>(shapes);
        JTextField coordinatesField = new JTextField(20);
        nameField = new JTextField(20);
        JButton drawButton = new JButton("Effectuer");
        infos = new JLabel("Choisissez une forme");
        JComboBox<String> operationsSelector = new JComboBox<>(new String[]{"Dessiner forme","deplacer un point", "calculer la distance avec"});  
            JPanel controlPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rangées, 2 colonnes, avec des espaces de 10 pixels

            // Première rangée
            controlPanel.add(new JLabel("Sélectionnez une forme:"));
            controlPanel.add(shapeSelector);

            // Deuxième rangée
            controlPanel.add(new JLabel("Entrez les coordonnées:"));
            controlPanel.add(coordinatesField);

            // Troisième rangée
            controlPanel.add(new JLabel("Nommez votre forme:"));
            controlPanel.add(nameField);

            // Quatrième rangée
            controlPanel.add(new JLabel("Choisissez une opération:"));
            controlPanel.add(operationsSelector);

            // Cinquième rangée
            controlPanel.add(infos);
            controlPanel.add(new JLabel(""));
            controlPanel.add(new JLabel(""));
            controlPanel.add(drawButton);

        shapeSelector.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedShape = e.getItem().toString();
                infos.setText("Attributs:" + controleur.updateInfo(selectedShape));
            }
        });

        drawButton.addActionListener(e -> {
            String name = nameField.getText().isEmpty() ? shapeSelector.getSelectedItem().toString() : nameField.getText();
            String message = controleur.addObjet(shapeSelector.getSelectedItem().toString(), coordinatesField.getText(), name);
            JOptionPane.showMessageDialog(this, message);
        });

        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(vue, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1300, 1000));
        this.setLocationRelativeTo(null);
    }

    private void setupWindowListener() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                controleur.recalculPoint();
            }
        });
    }
}
