package p5;

import java.util.*;

public class SortedList<T extends Comparable<T>> extends ArrayList<T> implements Comparator<T> {
    private ArrayList<Comparator<? super T>> compar;

    public SortedList(List<T> list) {
        this.compar = new ArrayList<>();
        this.addAll(list);
        Collections.sort(this, this);
    }

    public SortedList() {
        this.compar = new ArrayList<>();
    }

    public void addCriterion(Comparator<? super T> comparator) {
        this.compar.add(comparator);
        Collections.sort(this, this);
    }

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
}
