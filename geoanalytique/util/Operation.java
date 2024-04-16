package geoanalytique.util;


/**
 * Interface définissant une opération générique qui peut être appliquée dans le contexte de l'application.
 * Cette interface est utilisée pour encapsuler des comportements spécifiques en tant qu'objets qui peuvent
 * être exécutés, interrogés et manipulés de manière uniforme.
 */
public interface Operation {

    /**
     * Obtient le titre de l'opération.
     * @return Une chaîne de caractères représentant le titre de l'opération.
     */
    String getTitle() ;

    /**
     * Obtient l'arité de l'opération, c'est-à-dire le nombre d'arguments que l'opération prend.
     * @return Un entier représentant le nombre d'arguments nécessaires pour cette opération.
     */
    int getArite();

    /**
     * Définit un argument de l'opération à un index spécifié.
     * @param num L'index de l'argument à définir.
     * @param o L'objet qui représente la valeur de l'argument.
     */
    void setArgument(int num, Object o)throws Exception ;

    /**
     * Obtient la classe du type d'argument à un index spécifié.
     * @param num L'index de l'argument dont on veut connaître le type.
     * @return La classe du type d'argument.
     */
    @SuppressWarnings("rawtypes")
    Class getClassArgument(int num) throws Exception;

    /**
     * Exécute le calcul ou l'opération représentée par cette instance.
     * @return Un objet résultant du calcul de l'opération.
     */
    Object calculate() ;

    /**
     * Obtient une description de l'argument à un index spécifié.
     * @param num L'index de l'argument.
     * @return Une chaîne de caractères décrivant l'argument.
     */
    String getDescriptionArgument(int num) throws Exception;
}
