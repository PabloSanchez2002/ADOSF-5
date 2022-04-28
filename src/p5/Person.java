package p5;

import java.time.LocalDate;
import java.time.Period;

public class Person implements Comparable<Person> {
    String nombre;
    LocalDate fecha;

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

}
