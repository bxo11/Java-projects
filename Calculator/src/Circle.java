public class Circle extends Figure implements Printable {
    double r;

    public Circle(double x) {
        r = x;
    }

    @Override
    double calculateArea() {
        return Math.PI * r * r;
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public void print() {
        System.out.println("Radius: " + r);
        System.out.println("Area: " + calculateArea());
        System.out.println("Per: " + calculatePerimeter());
    }
}
