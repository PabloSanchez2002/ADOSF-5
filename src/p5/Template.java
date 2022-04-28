package p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Template<T extends Comparable<T>> {
    private SortedList<T> sorted;
    private ArrayList<Line> lines;
    private ArrayList<Predicates> preds;

    public Template() {
        this.sorted = new SortedList<>();
        this.lines = new ArrayList<>();
        this.preds = new ArrayList<>();
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

    // Clase predicados
    public class Predicates {
        private String s;
        private Predicate<T> p;

        public Predicates(String s, Predicate<T> p) {
            this.s = s;
            this.p = p;
        }

        public String getString() {
            return this.s;
        }

        public Predicate<T> getPredicate() {
            return this.p;
        }
    }

    // Clase Lambdas (devuelve collection)
    public class Lambdas extends Line {
        private Function<T, ArrayList<T>> funct;

        @SafeVarargs
        public Lambdas(Function<T, ArrayList<T>> funct, String s, Function<T, Object>... others) {
            super(s, others);
            this.funct = funct;
        }

        public Function<T, ArrayList<T>> getfunct() {
            return this.funct;
        }

        public String execute(T p) {
            ArrayList<T> list = this.funct.apply(p);
            String replace = this.getString();
            for (T t : list) {
                for (Function<T, Object> f : this.getFunciones()) {
                    replace = replace.replaceFirst("##", f.apply(t).toString());
                }
                replace = replace + "\n";
            }
            return replace;
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
                if (l instanceof Template.Lambdas) {
                    Lambdas lamb = (Lambdas) l;
                    s = s + lamb.execute(p);
                } else {
                    String replace = l.getString();
                    for (Function<T, Object> f : l.getFunciones()) {
                        replace = replace.replaceFirst("##", f.apply(p).toString());
                    }
                    s = (s + replace + "\n");
                }

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

    public void addWhen(Predicate<T> p, String s) {
        this.preds.add(new Predicates(s, p));
    }

    @SafeVarargs
    public final void addForEach(Function<T, ArrayList<T>> funct, String s, Function<T, Object>... others) {
        this.lines.add(new Lambdas(funct, s, others));
    }

}
