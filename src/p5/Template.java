package p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import p5.ej4.Strategy;

/**
 * Class of the Template
 * 
 * @author Pablo Sanchez and Alvaro Rodriguez
 */
public class Template<T extends Comparable<T>> {
    /**
     * List of sorted people
     */
    private SortedList<T> sorted;

    /**
     * List of line method
     */
    private ArrayList<Line> lines;

    /**
     * List of predicates
     */
    private ArrayList<Predicates> preds;

    /**
     * List of composed lambda functions
     */
    private ArrayList<Lambdas<? extends Object>> lambdas;

    /**
     * List of strategies
     */
    public ArrayList<Strategy<T>> estrategs;

    /**
     * Contructor of Template
     */
    public Template() {
        this.sorted = new SortedList<>();
        this.lines = new ArrayList<>();
        this.preds = new ArrayList<>();
        this.lambdas = new ArrayList<>();
        this.estrategs = new ArrayList<>();
    }

    /**
     * Class of the Line
     * 
     * @author Pablo Sanchez and Alvaro Rodriguez
     */
    public class Line {
        /**
         * String with ## to replace
         */
        private String s;

        /**
         * ArrayList with functions to work with
         */
        private ArrayList<Function<T, Object>> funciones;

        /**
         * Contructor
         * 
         * @param s         string
         * @param funciones funciones
         */
        @SafeVarargs
        public Line(String s, Function<T, Object>... funciones) {
            this.s = s;
            this.funciones = new ArrayList<>(Arrays.asList(funciones));
        }

        /**
         * 
         * @return the string of the object
         */
        public String getString() {
            return this.s;
        }

        /**
         * @return ArrayList of all the functions
         */
        public ArrayList<Function<T, Object>> getFunciones() {
            return this.funciones;
        }
    }

    /**
     * Class of the Predicates
     * 
     * @author Pablo Sanchez and Alvaro Rodriguez
     */
    public class Predicates {
        /**
         * String to include if predicate is true
         */
        private String s;

        /**
         * Predicate to evaluate
         */
        private Predicate<T> p;

        /**
         * Contructor
         * 
         * @param s string
         * @param p predicate
         */
        public Predicates(String s, Predicate<T> p) {
            this.s = s;
            this.p = p;
        }

        /**
         * 
         * @return string of object
         */
        public String getString() {
            return this.s;
        }

        /**
         * 
         * @return predicate
         */
        public Predicate<T> getPredicate() {
            return this.p;
        }
    }

    /**
     * Class of the Template (devuelve collection)
     * 
     * @author Pablo Sanchez and Alvaro Rodriguez
     */
    public class Lambdas<R> {

        /**
         * String with ## to repalce
         */
        private String s;

        /**
         * Array of funciones
         */
        private ArrayList<Function<R, Object>> funciones;

        /**
         * Function that returns an Array of R
         */
        private Function<T, ArrayList<R>> funct;

        /**
         * Contructor
         * 
         * @param funct  Function that returns an Array of R
         * @param s      String
         * @param others Array of funciones
         */
        @SafeVarargs
        public Lambdas(Function<T, ArrayList<R>> funct, String s, Function<R, Object>... others) {
            this.s = s;
            this.funct = funct;
            this.funciones = new ArrayList<>(Arrays.asList(others));
        }

        /**
         * 
         * @return
         */
        public Function<T, ArrayList<R>> getfunct() {
            return this.funct;
        }

        public String getString() {
            return this.s;
        }

        public ArrayList<Function<R, Object>> getFunciones() {
            return this.funciones;
        }

        public String execute(T p) {
            ArrayList<R> list = this.funct.apply(p);
            String replace = "";
            String pet = "";
            if (list.size() > 0) {
                for (R r : list) {
                    pet = this.s + "\n";
                    for (Function<R, Object> f : this.getFunciones()) {
                        pet = pet.replaceFirst("##", f.apply(r).toString());
                    }
                    replace = replace + pet;
                }
            }
            return replace;
        }

    }

    @SafeVarargs
    public final Template<T> add(String s, Function<T, Object>... funciones) {
        this.lines.add(new Line(s, funciones));
        return this;
    }

    @SafeVarargs
    public final Template<T> addObjects(T... list) {
        for (T o : list) {
            this.sorted.add(o);
        }
        return this;
    }

    public void withSortingCriteria(Comparator<? super T> comp) {
        this.sorted.addCriterion(comp);
    }

    public Map<T, String> emit() {
        Map<T, String> map = new HashMap<>();

        for (T p : this.sorted) {
            String s = emit(p);
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
        for (Predicates pred : this.preds) {
            if (pred.p.test(p)) {
                s = s + pred.s + "\n";
            }
        }
        for (Lambdas<? extends Object> lam : this.lambdas) {
            s = s + lam.execute(p);
        }

        for (Strategy<T> strat : this.estrategs) {
            s = strat.execute(s, p);
        }

        return s;
    }

    public Template<T> addWhen(Predicate<T> p, String s) {
        this.preds.add(new Predicates(s, p));
        return this;
    }

    @SafeVarargs
    public final <R> void addForEach(Function<T, ArrayList<R>> funct, String s, Function<R, Object>... others) {
        this.lambdas.add(new Lambdas<R>(funct, s, others));
    }

    @SafeVarargs
    public final void withOptions(Strategy<T>... strats) {
        this.estrategs = new ArrayList<>(Arrays.asList(strats));
    }

    public void executeStrategy() {
        for (T p : this.sorted) {
            String s = emit(p);
            for (Strategy<T> strat : this.estrategs) {
                strat.execute(s, p);
            }
        }
    }
}
