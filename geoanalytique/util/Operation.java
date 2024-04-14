package geoanalytique.util;


public interface Operation {
   
        String getTitle();
        int getArite();
        void setArgument(int num, Object o);
        @SuppressWarnings("rawtypes")
        Class getClassArgument(int num);
        Object calculate();
        String getDescriptionArgument(int num);
    
}
