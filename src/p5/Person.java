package p5;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Person implements Comparable<Person> {
    private String nombre;
    private LocalDate fecha;
    private ArrayList<Mascot> mascotas;

    public Person(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getName() {
        return this.nombre;
    }

    public LocalDate getBirthDate() {
        return this.fecha;
    }

    public int getAge() {
        return Period.between(this.fecha, LocalDate.now()).getYears();
    }

    @Override
    public int compareTo(Person o) {
        return this.nombre.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return (this.nombre + " (born: " + this.fecha + ")");
    }

    public void addMascots(Mascot... m) {
        for (Mascot mas : m) {
            this.mascotas.add(mas);
        }
    }

    public ArrayList<Mascot> getMascots() {
        return this.mascotas;
    }

}
