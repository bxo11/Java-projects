package com.company;

import java.util.*;

public class CarShowroomContainer {
    Map<String, CarShowroom> salony = new LinkedHashMap<>();

    public void addCenter(String s, int d) {
        salony.put(s, new CarShowroom(s, d));
    }

    public void removeCenter(String s) {
        salony.remove(s);
    }

    public List<CarShowroom> findEmpty() {
        List<CarShowroom> tab = new ArrayList<>();
        for (String key : salony.keySet()) {
            if (salony.get(key).getLista_samochodow().size() == 0) {
                tab.add(salony.get(key));
            }
        }
        return tab;
    }

    public void summary() {
        float pojemnosc;
        float size;
        for (String key : salony.keySet()) {
            pojemnosc = salony.get(key).getPojemnosc();
            size = salony.get(key).getLista_samochodow().size();
            System.out.println(key + ", " + (size / pojemnosc) * 100 + "%");
        }
    }

    public void sortByAmount() {
        List<Map.Entry<String, CarShowroom>> list =
                new LinkedList<Map.Entry<String, CarShowroom>>(salony.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, CarShowroom>>() {
            public int compare(Map.Entry<String, CarShowroom> o1,
                               Map.Entry<String, CarShowroom> o2) {
                return Integer.compare(o1.getValue().getPojemnosc(), o2.getValue().getPojemnosc());
            }
        });

        salony.clear();
        for (Map.Entry<String, CarShowroom> entry : list) {
            salony.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, CarShowroom> getSalony() {
        return salony;
    }

    public void setSalony(Map<String, CarShowroom> salony) {
        this.salony = salony;
    }

}
