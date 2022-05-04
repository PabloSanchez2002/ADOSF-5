package p5;

import java.util.*;

/**
 * Class of the SortedList
 * 
 * @author Pablo Sanchez and Alvaro Rodriguez
 */
public class SortedList<T extends Comparable<T>> extends ArrayList<T> implements Comparator<T> {
    /**
     * ArrayList of comparators of the sorted list
     */
    private ArrayList<Comparator<? super T>> compar;

    /**
     * Contructor
     * 
     * @param list of elements
     */
    public SortedList(List<T> list) {
        this.compar = new ArrayList<>();
        this.addAll(list);
        Collections.sort(this, this);
    }

    /**
     * Contructor with empty list
     */
    public SortedList() {
        this.compar = new ArrayList<>();
    }

    /**
     * Adds a comprarator to the object
     * 
     * @param comparator
     */
    public void addCriterion(Comparator<? super T> comparator) {
        this.compar.add(comparator);
        Collections.sort(this, this);
    }

    /**
     * Comparator method
     */
    @Override
    public int compare(T arg0, T arg1) {
        int dif = arg0.compareTo(arg1);
        if (dif == 0) {
            for (Comparator<? super T> c : this.compar) {
                dif = c.compare(arg0, arg1);
                if (dif != 0) {
                    return dif;
                }
            }
        }
        return dif;
    }

    /**
     * Adds a single element to the object
     */
    @Override
    public boolean add(T t) {
        boolean b = super.add(t);
        Collections.sort(this, this);
        return b;
    }
}
