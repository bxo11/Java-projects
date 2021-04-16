package com.company;

import java.io.*;
import java.util.*;
import java.io.Serializable;

public class CarShowroomContainer implements Serializable {
    Map<String, CarShowroom> salons = new TreeMap<>();

    public void addCenter(String s, int d) {
        salons.put(s, new CarShowroom(s, d));
    }

    public void removeCenter(String s) {
        salons.remove(s);
    }

    public List<CarShowroom> findEmpty() {
        List<CarShowroom> tab = new ArrayList<>();
        for (String key : salons.keySet()) {
            if (salons.get(key).getLista_samochodow().size() == 0) {
                tab.add(salons.get(key));
            }
        }
        return tab;
    }

    public void summary() {
        float pojemnosc;
        float size;
        for (String key : salons.keySet()) {
            pojemnosc = salons.get(key).getPojemnosc();
            size = salons.get(key).getLista_samochodow().size();
            System.out.println(key + ", " + (size / pojemnosc) * 100 + "%");
        }
    }

    public void sortByAmount() {
        List<Map.Entry<String, CarShowroom>> list =
                new LinkedList<Map.Entry<String, CarShowroom>>(salons.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, CarShowroom>>() {
            public int compare(Map.Entry<String, CarShowroom> o1,
                               Map.Entry<String, CarShowroom> o2) {
                return Integer.compare(o1.getValue().getPojemnosc(), o2.getValue().getPojemnosc());
            }
        });

        salons.clear();
        for (Map.Entry<String, CarShowroom> entry : list) {
            salons.put(entry.getKey(), entry.getValue());
        }
    }

    public void serialization(String filename) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);
            out.close();
            file.close();

        } catch (IOException e) {
            System.out.println("serialization, IOException is caught");
        }
    }

    public CarShowroomContainer deserialization(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            CarShowroomContainer object = (CarShowroomContainer) in.readObject();
            in.close();
            file.close();

            return object;
        } catch (IOException e) {
            System.out.println("deserialisation ,IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return null;
    }

    public Map<String, CarShowroom> getSalons() {
        return salons;
    }

    public void setSalons(Map<String, CarShowroom> salons) {
        this.salons = salons;
    }

}
