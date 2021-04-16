package com.company;

public class DataGenerator {

    public CarShowroomContainer generate() {

//        Vehicle v1 = new Vehicle("Ford", "X", itemCondition.NEW, 50000, 2015, 1000, 500, 5);
//        Vehicle v2 = new Vehicle("Skoda", "O", itemCondition.NEW, 60000, 2014, 7000, 300, 4);
//        Vehicle v3 = new Vehicle("Honda", "B", itemCondition.NEW, 70, 1999, 4500, 600, 1);
//
//        Vehicle v4 = new Vehicle("Opel", "A", itemCondition.USED, 5000, 1999, 10000, 500, 5);
//        Vehicle v5 = new Vehicle("BMW", "Y", itemCondition.DAMAGED, 6000, 2000, 70, 300, 4);

      /*  CarShowroom salon1=new CarShowroom("salon1",4);
        salon1.addProduct(v1);

        CarShowroom salon2=new CarShowroom("salon1",2);
        salon2.addProduct(v4);

        System.out.println(v1);
        System.out.println(v4.compareTo(v5));

        salon1.getProduct(v3);
        salon1.getProduct(v2);
        //salon1.removeProduct(v1);
      // System.out.println(salon2.search("a"));
       //salon2.searchPartial("a");
       //System.out.println(salon1.countByCondition(itemCondition.NEW));
        //salon1.sortByAmount();
        salon1.sortByName();
        //salon1.summary();
        System.out.println(salon1.max());
    */
        CarShowroomContainer container = new CarShowroomContainer();
        container.addCenter("salon1", 10);
        container.addCenter("salon2", 20);
        container.addCenter("salon3", 30);
        container.addCenter("salon4", 40);
        container.addCenter("salon5", 50);

        // container.addCenter("salon4",7);
        // container.addCenter("salon5",8);
        //container.removeCenter("salon4");
        container.getSalons().get("salon1").addProduct(new Vehicle("Ford", "A", itemCondi.NEW, 50000, 2015, 1000, 500, 5, 111, "electricity"));
        container.getSalons().get("salon1").addProduct(new Vehicle("Skoda", "B", itemCondi.NEW, 60000, 2014, 7000, 200, 4, 222, "gasoline"));
        container.getSalons().get("salon1").addProduct(new Vehicle("Honda", "C", itemCondi.NEW, 70000, 2004, 4500, 600, 1, 333, "diesel"));
        container.getSalons().get("salon1").addProduct(new Vehicle("Opel", "D", itemCondi.USED, 5000, 2001, 10000, 100, 5, 444, "diesel"));
        container.getSalons().get("salon1").addProduct(new Vehicle("BMW", "E", itemCondi.DAMAGED, 6000, 2000, 70, 300, 4, 555, "gasoline"));

        container.getSalons().get("salon2").addProduct(new Vehicle("Opel", "F", itemCondi.USED, 7000, 2002, 10000, 500, 5, 666, "diesel"));
        container.getSalons().get("salon2").addProduct(new Vehicle("BMW", "G", itemCondi.DAMAGED, 4000, 1998, 70, 300, 4, 777, "diesel"));
        container.getSalons().get("salon2").addProduct(new Vehicle("Ford", "H", itemCondi.NEW, 60000, 2016, 1000, 500, 5, 888, "gasoline"));

        container.getSalons().get("salon3").addProduct(new Vehicle("Ford", "I", itemCondi.NEW, 70000, 2017, 1000, 500, 5, 999, "electricity"));
        container.getSalons().get("salon3").addProduct(new Vehicle("Skoda", "J", itemCondi.NEW, 70000, 2016, 7000, 300, 4, 10101010, "gasoline"));
        container.getSalons().get("salon3").addProduct(new Vehicle("Honda", "K", itemCondi.NEW, 100000, 2005, 4500, 600, 1, 11111111, "diesel"));

        container.getSalons().get("salon4").addProduct(new Vehicle("Skoda", "L", itemCondi.NEW, 80000, 2018, 7000, 300, 4, 12121212, "gasoline"));
        container.getSalons().get("salon4").addProduct(new Vehicle("Honda", "M", itemCondi.NEW, 40000, 2006, 4500, 600, 1, 13131313, "diesel"));

        //System.out.println(container.findEmpty());
        //container.summary();

        return container;
    }
}
