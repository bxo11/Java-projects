public class Prism extends Figure3D implements Printable {
    double a, b, c, h;
    int type;

    public Prism(double a, double h) {
        this.a = a;
        this.h = h;
        type = 1;
    }

    public Prism(double a, double b, double h) {
        this.a = a;
        this.b = b;
        this.h = h;
        type = 2;
    }

    public Prism(double a, double b, double c, double h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = h;
        type = 3;
    }

    @Override
    double calculateArea() {
        switch (type) {
            case 1:
                return (2 * Math.PI * a * a) + 2 * Math.PI * a * h;
            case 2:
                return (2 * a * b) + 2 * a * h + 2 * b * h;
            case 3:
                double p = (a + b + c) / 2;
                return (2 * Math.sqrt(p * (p - a) * (p - b) * (p - c))) + a * h + b * h + c * h;
        }
        return 0;
    }

    @Override
    double calculateVolume() {
        switch (type) {
            case 1:
                return (Math.PI * a * a) * h;
            case 2:
                return (a * b) * h;
            case 3:
                double p = (a + b + c) / 2;
                return (Math.sqrt(p * (p - a) * (p - b) * (p - c))) * h;
        }
        return 0;
    }

    @Override
    public void print() {
        switch (type) {
            case 1:
                System.out.println("Dim: " + a + " x " + h);
                break;
            case 2:
                System.out.println("Dim: " + a + " x " + b + " x " + h);
                break;
            case 3:
                System.out.println("Dim: " + a + " x " + b + " x " + c + " x " + h);
                break;
        }
        System.out.println("Area: " + calculateArea());
        System.out.println("Volume: " + calculateVolume());
    }
}
