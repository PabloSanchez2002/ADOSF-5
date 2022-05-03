package p5.ej4;

/**
 * Class of the TimeStamper
 * 
 * @author Pablo Sanchez and Alvaro Rodriguez
 */
public class TimeStamper<T> extends Strategy<T> {

    /**
     * Executes the functionality
     * 
     * @param s string to process
     * @param p person
     * @return String
     */
    @Override
    public String execute(String s, T p) {
        return java.time.LocalDate.now().toString() + "\n" + s;
    }

}
