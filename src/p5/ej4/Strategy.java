package p5.ej4;

/**
 * Class of the abstract class Strategy
 * 
 * @author Pablo Sanchez and Alvaro Rodriguez
 */
public abstract class Strategy<T> {

    /**
     * Contructor
     */
    public Strategy() {
    }

    /**
     * Executes the functionality
     * 
     * @param s string to process
     * @param p person
     * @return String
     */
    public abstract String execute(String s, T p);

}
