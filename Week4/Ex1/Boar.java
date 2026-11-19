public class Boar {
    private final String cook;
    private final int number;

    public Boar(String cook, int number) {
        this.cook = cook;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "boar number " + number + " cooked by " + cook;
    }
}
