package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Vehicle v1 = new Vehicle("Ford", "X", itemCondition.NEW, 50000, 2015, 1000, 500, 5);
        Vehicle v2 = new Vehicle("Skoda", "O", itemCondition.NEW, 60000, 2014, 7000, 300, 4);
        Vehicle v3 = new Vehicle("Honda", "B", itemCondition.NEW, 70, 1999, 4500, 600, 1);

        Vehicle v4 = new Vehicle("Opel", "A", itemCondition.USED, 5000, 1999, 10000, 500, 5);
        Vehicle v5 = new Vehicle("BMW", "Y", itemCondition.DAMAGED, 6000, 2000, 70, 300, 4);

      /*  CarShowroom salon1=new CarShowroom("salon1",4);
        salon1.addProduct(v1);
        salon1.addProduct(v2);
        salon1.addProduct(v3);
        salon1.addProduct(v3);

        CarShowroom salon2=new CarShowroom("salon1",2);
        salon2.addProduct(v4);
        salon2.addProduct(v5);
        salon2.addProduct(v5);

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
        container.addCenter("salon1", 16);
        container.addCenter("salon2", 12);
        container.addCenter("salon3", 99);
        container.addCenter("salon4", 50);
        container.addCenter("salon5", 2);

        // container.addCenter("salon4",7);
        // container.addCenter("salon5",8);
        //container.removeCenter("salon4");
        container.getSalony().get("salon1").addProduct(v1);
        container.getSalony().get("salon1").addProduct(v2);
        container.getSalony().get("salon1").addProduct(v3);
        container.getSalony().get("salon1").addProduct(v4);
        container.getSalony().get("salon1").addProduct(v5);

        container.getSalony().get("salon2").addProduct(v4);
        container.getSalony().get("salon2").addProduct(v5);
        container.getSalony().get("salon2").addProduct(v1);

        container.getSalony().get("salon3").addProduct(v1);
        container.getSalony().get("salon3").addProduct(v2);
        container.getSalony().get("salon3").addProduct(v3);

        container.getSalony().get("salon4").addProduct(v2);
        container.getSalony().get("salon4").addProduct(v3);

        //System.out.println(container.findEmpty());
        //container.summary();

        App app = new App("Lab 4", container);
    }
}
