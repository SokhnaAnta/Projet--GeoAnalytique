package geoanalytique.model;

/**
 * Classe représentant un viewport (zone d'affichage) dans un système de coordonnées.
 * Elle gère la conversion entre les coordonnées du système de coordonnées du modèle et les coordonnées du système de pixels de l'écran.
 */
public class ViewPort {
    private int largeur;   // Largeur du viewport en pixels
    private int longueur;  // Longueur du viewport en pixels
    private double xMin;   // Limite minimale sur l'axe des X en unités du modèle
    private double xMax;   // Limite maximale sur l'axe des X en unités du modèle
    private double yMin;   // Limite minimale sur l'axe des Y en unités du modèle
    private double yMax;   // Limite maximale sur l'axe des Y en unités du modèle
    private double echelle;  // Facteur de mise à l'échelle entre les unités du modèle et les pixels

    /**
     * Constructeur qui initialise le viewport avec des dimensions et une échelle spécifiques.
     * @param largeur Largeur du viewport en pixels.
     * @param longueur Longueur du viewport en pixels.
     * @param echelle Facteur de mise à l'échelle entre les unités du modèle et les pixels.
     */
    public ViewPort(int largeur, int longueur, double echelle) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.echelle = echelle;
        // Centrez le viewport autour de (0,0)
        this.xMin = -largeur / 2 / echelle;
        this.xMax = largeur / 2 / echelle;
        this.yMin = -longueur / 2 / echelle;
        this.yMax = longueur / 2 / echelle;
    }

    /**
     * Convertit une coordonnée X du modèle en une coordonnée X de pixel sur l'écran.
     * @param x Coordonnée X du modèle.
     * @return Coordonnée X en pixels.
     */
    public int convertX(double x) {
        return (int) ((x * echelle) + largeur / 2);
    }

    /**
     * Convertit une coordonnée Y du modèle en une coordonnée Y de pixel sur l'écran.
     * @param y Coordonnée Y du modèle.
     * @return Coordonnée Y en pixels.
     */
    public int convertY(double y) {
        return (int) ((-y * echelle) + longueur / 2);
    }

    /**
     * Convertit une coordonnée X de pixel en une coordonnée X du modèle.
     * @param x Coordonnée X en pixels.
     * @return Coordonnée X du modèle.
     */
    public double convertXInverse(int x) {
        return (x - largeur / 2) / echelle;
    }

    /**
     * Convertit une coordonnée Y de pixel en une coordonnée Y du modèle.
     * @param y Coordonnée Y en pixels.
     * @return Coordonnée Y du modèle.
     */
    public double convertYInverse(int y) {
        return -(y - longueur / 2) / echelle;
    }

    // Getters et setters pour accéder et modifier les propriétés du viewport
    public double getEchelle() {
        return echelle;
    }

    public void setEchelle(double echelle) {
        this.echelle = echelle;
        this.xMin = -largeur / 2 / echelle;
        this.xMax = largeur / 2 / echelle;
        this.yMin = -longueur / 2 / echelle;
        this.yMax = longueur / 2 / echelle;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
        this.xMin = -largeur / 2 / echelle;
        this.xMax = largeur / 2 / echelle;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
        this.yMin = -longueur / 2 / echelle;
        this.yMax = longueur / 2 / echelle;
    }

    public double getxMin() {
        return xMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }
}
