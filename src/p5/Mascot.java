package p5;

/**
 * Class of the Mascot
 * 
 * @author Pablo Sanchez and Alvaro Rodriguez
 */
public class Mascot {

    /**
     * Mascot type
     */
    private String type;

    /**
     * Mascot name
     */
    private String name;

    /**
     * Contructor
     * 
     * @param type type of mascot
     * @param name name of mascot
     */
    public Mascot(String type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @return String
     */
    public String getMascotType() {
        return this.type;
    }

    /**
     * @return String
     */
    public String getMascotName() {
        return this.name;
    }

}
