package geoanalytique.graphique;
import java.awt.Graphics;
import java.awt.FontMetrics;


/**
 * Classe représentant un texte graphique sur une interface utilisateur.
 */
public class GTexte extends Graphique {
    private String texte; // Texte à afficher
    private int largeur; // Largeur du texte
    private int longueur; // Hauteur du texte
    private int descent; // Descente du texte pour ajustements de placement


      /**
     * Constructeur pour créer un objet texte graphique.
     * @param x Position x où le texte doit être affiché.
     * @param y Position y où le texte doit être affiché.
     * @param texte Le texte à afficher.
     * @param nom Le nom du graphique.
     */
    public GTexte(int x, int y, String texte,String nom) {
        super(x, y,nom);
        this.texte = texte;
    }



    @Override
    public void dessiner(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        largeur = fm.stringWidth(texte);
        longueur = fm.getHeight();
        descent = fm.getDescent();  // Stocker la descente pour l'utiliser dans contientPoint
        // Dessiner le texte en ajustant la position y pour aligner correctement le texte.
        g.drawString(texte, x, y);
    }

    public boolean contientPoint(int x, int y) {
        // Utiliser la descente stockée pour vérifier la position
        return x >= this.x && x <= this.x + largeur && y >= this.y - longueur + descent && y <= this.y + descent;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
