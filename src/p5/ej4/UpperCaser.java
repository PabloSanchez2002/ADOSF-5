package p5.ej4;

/**
 * Class of the UpperCaser
 * 
 * @author Pablo Sanchez and Alvaro Rodriguez
 */
public class UpperCaser<T> extends Strategy<T> {

    /**
     * Executes the functionality
     * 
     * @param s string to process
     * @param p person
     * @return String
     */
    @Override
    public String execute(String s, T p) {
        return s.toUpperCase();
    }

}
