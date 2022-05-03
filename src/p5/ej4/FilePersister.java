package p5.ej4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class of the FilePersister
 * 
 * @author Pablo Sanchez and Alvaro Rodriguez
 */
public class FilePersister<T> extends Strategy<T> {
    /**
     * Function that the class uses
     */
    private Function<T, ? extends Object> func;

    /**
     * Map to count how many times a name is repeted
     */
    private Map<String, Integer> map;

    /**
     * Contructor
     * 
     * @param func function of the class
     */
    public FilePersister(Function<T, ? extends Object> func) {
        this.func = func;
        map = new HashMap<String, Integer>();
    }

    /**
     * Executes the functionality
     * 
     * @param s string to process
     * @param p person
     * @return String
     */
    @Override
    public String execute(String s, T p) {
        if (map.containsKey(func.apply(p))) {
            map.merge((String) func.apply(p), 1, Integer::sum);
        } else {
            map.put((String) func.apply(p), 0);
        }
        String filename = (String) func.apply(p) + map.get(func.apply(p)) + ". java";

        try {
            File file = new File(filename);
            file.createNewFile();
            FileWriter myWriter = new FileWriter(filename, false);
            myWriter.write(s);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return s;
    }

}
