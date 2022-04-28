package p5;

public class Mascot {
    private String type;
    private String name;

    public Mascot(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getMascotType() {
        return this.type;
    }

    public String getMascotName() {
        return this.name;
    }

}
