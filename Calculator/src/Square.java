import gah.*

public class Square extends Figure implements Printable {
    double a, b;

    public Square(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    double calculateArea() {
        return a * b;
    }

    @Override
    double calculatePerimeter() {
        return (2 * a) + (2 * b);
    }

    @Override
    public void print() {
        System.out.println("Size: " + a + " x " + b);
        System.out.println("Area: " + calculateArea());
        System.out.println("Per: " + calculatePerimeter());
    }
}
