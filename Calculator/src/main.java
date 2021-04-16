import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        boolean cons = true;
        while (cons) {
            Scanner scanner = new Scanner(System.in);
            int type;
            double a, b, c, h;

            System.out.println("Press key ");
            System.out.println("1 - Circle");
            System.out.println("2 - Square");
            System.out.println("3 - Triangle");
            System.out.println("4 - Prism");
            System.out.println("5 - Exit");
            type = scanner.nextInt();

            switch (type) {
                case 1:
                    System.out.println("Insert data");
                    System.out.println("r: ");
                    a = scanner.nextDouble();
                    if (a <= 0) {
                        System.out.println("Error");
                        break;
                    }
                    Circle cir = new Circle(a);
                    cir.print();
                    break;
                case 2:
                    System.out.println("Insert data");
                    System.out.println("a: ");
                    a = scanner.nextDouble();
                    System.out.println("b: ");
                    b = scanner.nextDouble();
                    if (a <= 0 || b <= 0) {
                        System.out.println("Error");
                        break;
                    }
                    Square sq = new Square(a, b);
                    sq.print();
                    break;
                case 3:
                    System.out.println("Insert data");
                    System.out.println("a: ");
                    a = scanner.nextDouble();
                    System.out.println("b: ");
                    b = scanner.nextDouble();
                    System.out.println("c: ");
                    c = scanner.nextDouble();
                    if (a <= 0 || b <= 0 || c <= 0) {
                        System.out.println("Error");
                        break;
                    }
                    if (a + b <= c || a + c <= b || b + c <= a) {
                        System.out.println("Error");
                        break;
                    }
                    Triangle tr = new Triangle(a, b, c);
                    tr.print();
                    break;
                case 4:
                    System.out.println("Choose base");
                    System.out.println("1 - Circle");
                    System.out.println("2 - Square");
                    System.out.println("3 - Triangle");
                    int base = scanner.nextInt();
                    switch (base) {
                        case 1:
                            System.out.println("Insert data");
                            System.out.println("r: ");
                            a = scanner.nextDouble();
                            System.out.println("h: ");
                            h = scanner.nextDouble();
                            if (a <= 0 || h <= 0) {
                                System.out.println("Error");
                                break;
                            }
                            Prism pr1 = new Prism(a, h);
                            pr1.print();
                            break;
                        case 2:
                            System.out.println("Insert data");
                            System.out.println("a: ");
                            a = scanner.nextDouble();
                            System.out.println("b: ");
                            b = scanner.nextDouble();
                            System.out.println("h: ");
                            h = scanner.nextDouble();
                            if (a <= 0 || b <= 0 || h <= 0) {
                                System.out.println("Error");
                                break;
                            }
                            Prism pr2 = new Prism(a, b, h);
                            pr2.print();
                            break;
                        case 3:
                            System.out.println("Insert data");
                            System.out.println("a: ");
                            a = scanner.nextDouble();
                            System.out.println("b: ");
                            b = scanner.nextDouble();
                            System.out.println("c: ");
                            c = scanner.nextDouble();
                            System.out.println("h: ");
                            h = scanner.nextDouble();
                            if (a <= 0 || b <= 0 || c <= 0 || h <= 0) {
                                System.out.println("Error");
                                break;
                            }
                            if (a + b <= c || a + c <= b || b + c <= a) {
                                System.out.println("Error");
                                break;
                            }
                            Prism pr3 = new Prism(a, b, c, h);
                            pr3.print();
                            break;
                    }
                case 5:
                    cons = false;
                    break;
            }

        }


    }
}
