package p5;

import java.util.*;

public class SortedList<T> extends ArrayList<T> implements Comparable<T> {

    public SortedList(List<T> list) {
        this.addAll(list);
    }

    public void addCriterion(Comparator<T> comparator) {
    }

    @Override
    public int compareTo(T o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
