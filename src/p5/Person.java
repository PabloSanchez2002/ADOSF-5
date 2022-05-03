package p5;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 * Class of the Person
 * 
 * @author Pablo Sanchez and Alvaro Rodriguez
 */
public class Person implements Comparable<Person> {
    /**
     * Name of person
     */
    private String nombre;

    /**
     * Date of birth
     */
    private LocalDate fecha;

    /**
     * Array that contains all the mascots owned
     */
    private ArrayList<Mascot> mascotas;

    public Person(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.mascotas = new ArrayList<>();
    }

    /**
     * @return String
     */
    public String getName() {
        return this.nombre;
    }

    /**
     * @return LocalDate
     */
    public LocalDate getBirthDate() {
        return this.fecha;
    }

    /**
     * @return int
     */
    public int getAge() {
        return Period.between(this.fecha, LocalDate.now()).getYears();
    }

    /**
     * @param o object to be ecompared to
     * @return int
     */
    @Override
    public int compareTo(Person o) {
        return this.nombre.compareTo(o.getName());
    }

    /**
     * Return the string of the Person
     * 
     * @return String
     */
    @Override
    public String toString() {
        return (this.nombre + " (born: " + this.fecha + ")");
    }

    /**
     * Adds a mascot to the person
     * 
     * @param m
     */
    public void addMascots(Mascot... m) {
        for (Mascot mas : m) {
            this.mascotas.add(mas);
        }
    }

    /**
     * Returns the mascots of the person
     * 
     * @return ArrayList<Mascot>
     */
    public ArrayList<Mascot> getMascots() {
        return this.mascotas;
    }

}
