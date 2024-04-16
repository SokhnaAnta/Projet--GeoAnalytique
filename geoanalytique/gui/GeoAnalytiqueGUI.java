package geoanalytique.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.view.GeoAnalytiqueView;


/**
 * Interface graphique principale pour l'application GeoAnalytique.
 */
public class GeoAnalytiqueGUI extends JFrame {
    private GeoAnalytiqueControleur controleur;
    private GeoAnalytiqueView vue;
    private JLabel infos;
    private JTextField nomField; 
    private int i = 0  ; // Compte le nombre d'objets créés

    public GeoAnalytiqueGUI(GeoAnalytiqueControleur controleur, GeoAnalytiqueView vue) {
        super("GeoAnalytique - Dessinez vos formes geometriques");
        this.controleur = controleur;
        this.vue = vue;
        initializeUI();
        setupWindowListener();
      
    }


     /**
     * Initialise l'interface utilisateur.
     */
    private void initializeUI() {
        String[] formes = {"Point", "Droite", "Segment", "Ellipse", "Cercle", "Parallelogramme", "Rectangle", "Carre", "Triangle"};
        JComboBox<String> formeselector = new JComboBox<>(formes);
        JTextField coordsField = new JTextField(20);
        nomField = new JTextField(20);
        JButton button = new JButton("Effectuer");
        infos = new JLabel("Choisissez une forme");
       
        JComboBox<String> operationsSelector = new JComboBox<>(new String[]{"Dessiner forme","deplacer un point", "calculer la distance avec"});  
            JPanel controlPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rangees, 2 colonnes, avec des espaces de 10 pixels
            // Première rangee
            controlPanel.add(new JLabel("Selectionnez une forme:"));
            controlPanel.add(formeselector);

            // Deuxième rangee
            controlPanel.add(new JLabel("Entrez les coordonnees:"));
            controlPanel.add(coordsField);

            // Troisième rangee
            controlPanel.add(new JLabel("Nommez votre forme:"));
            controlPanel.add(nomField);

            // Quatrième rangee
            controlPanel.add(new JLabel("Choisissez une operation:"));
            controlPanel.add(operationsSelector);

            // Cinquième rangee
            controlPanel.add(infos);
            controlPanel.add(new JLabel(""));
            controlPanel.add(new JLabel(""));
            controlPanel.add(button);

        formeselector.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedShape = e.getItem().toString();
                infos.setText("Attributs:" + controleur.updateInfo(selectedShape));
            }
        });

        button.addActionListener(e -> {
            if (operationsSelector.getSelectedItem().toString().equals("Dessiner forme")) {
            String name = nomField.getText().isEmpty() ? "GeoObject"+" "+i++ : nomField.getText();
            String message = controleur.addObjet(formeselector.getSelectedItem().toString(), coordsField.getText(), name);
            JOptionPane.showMessageDialog(this, message);
            }
            else{
                JOptionPane.showMessageDialog(this, "operation non implemente");
            }
        });
        
        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(vue, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1300, 1000));
        this.setLocationRelativeTo(null);
    }


    /**
     * Configure l'écouteur pour redimensionner les composants.
     */
    private void setupWindowListener() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                controleur.recalculPoint();
            }
        });
    }
}
