package geoanalytique.model;

public class ViewPort {
    private int largeur;
    private int longueur;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private double echelle;

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

    public int convertX(double x) {
        return (int) ((x * echelle) + largeur / 2);
    }

    public int convertY(double y) {
        return (int) ((-y * echelle) + longueur / 2);
    }

    public double convertXInverse(int x) {
        return (x - largeur / 2) / echelle;
    }

    public double convertYInverse(int y) {
        return -(y - longueur / 2) / echelle;
    }

    // Getters and Setters
    public double getEchelle() {
        return echelle;
    }

    public void setEchelle(double echelle) {
        this.echelle = echelle;
        // Recalculer les limites lorsque l'échelle change
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
        // Recalculer les limites lorsque la largeur change
        this.xMin = -largeur / 2 / echelle;
        this.xMax = largeur / 2 / echelle;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
        // Recalculer les limites lorsque la longueur change
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
