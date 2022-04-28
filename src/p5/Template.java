package p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Template<T extends Comparable<T>> {
    SortedList<T> sorted;
    ArrayList<Line> lines;

    public Template() {
        this.sorted = new SortedList<>();
        this.lines = new ArrayList<>();
    }

    //// Clase Line
    public class Line {
        private String s;
        private ArrayList<Function<T, Object>> funciones;

        @SafeVarargs
        public Line(String s, Function<T, Object>... funciones) {
            this.s = s;
            this.funciones = new ArrayList<>(Arrays.asList(funciones));
        }

        public String getString() {
            return this.s;
        }

        public ArrayList<Function<T, Object>> getFunciones() {
            return this.funciones;
        }
    }

    @SafeVarargs
    public final void add(String s, Function<T, Object>... funciones) {
        this.lines.add(new Line(s, funciones));
    }

    @SafeVarargs
    public final void addObjects(T... list) {
        for (T o : list) {
            this.sorted.add(o);
        }
    }

    public void withSortingCriteria(Comparator<? super T> comp) {
        this.sorted.addCriterion(comp);
    }

    public Map<T, String> emit() {
        Map<T, String> map = new HashMap<>();

        for (T p : this.sorted) {
            String s = "";
            for (Line l : this.lines) {
                String replace = l.getString();
                for (Function<T, Object> f : l.getFunciones()) {
                    replace = replace.replaceFirst("##", f.apply(p).toString());
                }
                s = (s + replace + "\n");
            }
            map.put(p, s);
        }
        return map;
    }

    public String emit(T p) {
        String s = "";
        for (Line l : this.lines) {
            String replace = l.getString();
            for (Function<T, Object> f : l.getFunciones()) {
                replace = replace.replaceFirst("##", f.apply(p).toString());
            }
            s = (s + replace + "\n");
        }
        return s;
    }

}
