package geoanalytique.graphique;
import java.awt.Graphics;
import java.awt.FontMetrics;

public class GTexte extends Graphique {
    private String texte; // Texte à afficher
    private int textWidth; // Largeur du texte
    private int textHeight; // Hauteur du texte
    private int descent; // Descente de la ligne de texte

    public GTexte(int x, int y, String texte,String nom) {
        super(x, y,nom);
        this.texte = texte;
    }

    @Override
    public void dessiner(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        textWidth = fm.stringWidth(texte);
        textHeight = fm.getHeight();
        descent = fm.getDescent();  // Stocker la descente pour l'utiliser dans contientPoint
        g.drawString(texte, x, y);
    }

    public boolean contientPoint(int x, int y) {
        // Utiliser la descente stockée pour vérifier la position
        return x >= this.x && x <= this.x + textWidth && y >= this.y - textHeight + descent && y <= this.y + descent;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
