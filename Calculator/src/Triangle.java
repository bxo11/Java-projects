public class Triangle extends Figure implements Printable {
    double a, b, c;
    double p;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double calculateArea() {
        p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    double calculatePerimeter() {
        return a + b + c;
    }

    @Override
    public void print() {
        System.out.println("Size: " + a + " x " + b + " x " + c);
        System.out.println("Area: " + calculateArea());
        System.out.println("Per: " + calculatePerimeter());
    }
}
