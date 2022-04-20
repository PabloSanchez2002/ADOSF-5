package p5;

import java.time.LocalDate;

public class Person implements Comparable<Person> {
    String nombre;
    LocalDate fecha;

    public Person(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return this.nombre;
    }

    public LocalDate getBirthDate() {
        return this.fecha;
    }

    @Override
    public int compareTo(Person o) {
        if (this.nombre.compareTo(o.nombre) < 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
