package geoanalytique.model.geoobject.operation;

//  les objets géométriques ont une méthode setName(String) pour changer le nom.
// Cette classe devra être définie quelque part dans votre projet.
import geoanalytique.model.GeoObject;
import geoanalytique.util.Operation;

public class ChangeNomOperation implements Operation {
    private GeoObject geoObject; // l'objet à renommer
    private String nouveauNom; // le nouveau nom pour l'objet

    // Constructeur qui prend le nouvel objet GeoObject et le nouveau nom.
    public ChangeNomOperation(GeoObject geoObject, String nouveauNom) {
        this.geoObject = geoObject;
        this.nouveauNom = nouveauNom;
    }

    // Titre de l'opération
    public String getTitle() {
        return "Changer le nom";
    }

    // Arité de l'opération, 1 car nous changeons le nom d'un seul GeoObject à la fois
    public int getArite() {
        return 1;
    }

    // Définir l'argument, dans ce cas, le nouveau nom de l'objet
    public void setArgument(int num, Object o) {
        if (num == 0 && o instanceof String) {
            nouveauNom = (String) o;
        } else {
            throw new IllegalArgumentException("Argument invalide pour ChangeNomOperation.");
        }
    }

    // Récupérer la classe de l'argument, ici String pour le nom
    @SuppressWarnings("rawtypes")
    public Class getClassArgument(int num) {
        if (num == 0) {
            return String.class;
        } else {
            throw new IllegalArgumentException("Argument numéro invalide pour ChangeNomOperation.");
        }
    }

    // Effectuer le changement de nom
    public Object calculate() {
        geoObject.setNom(nouveauNom);
        return geoObject; 
    }

    // Description de l'argument, ici le nouveau nom
    public String getDescriptionArgument(int num) {
        if (num == 0) {
            return "Nouveau nom de l'objet géométrique";
        } else {
            throw new IllegalArgumentException("Argument numéro invalide pour ChangeNomOperation.");
        }
    }
}
