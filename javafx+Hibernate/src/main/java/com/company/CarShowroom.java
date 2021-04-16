package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarShowroom implements Serializable {
    private String nazwa;
    private List<Vehicle> lista_samochodow = new ArrayList<>();
    private int pojemnosc;

    public CarShowroom(String nazwa, int pojemnosc) {
        this.nazwa = nazwa;
        this.pojemnosc = pojemnosc;
    }

    public void addProduct(Vehicle v) {
        if (lista_samochodow.size() < pojemnosc) {
            if (lista_samochodow.contains(v)) {
                v.setAmount(v.getAmount() + 1);
                v.setSalonName(nazwa);
            } else {
                lista_samochodow.add(v);
                v.setSalonName(nazwa);
            }
        } else {
            System.err.println("max limit");
        }
    }

    public void getProduct(Vehicle v) {
        if (v.getAmount() == 1) {
            lista_samochodow.remove(v);
        } else {
            v.setAmount(v.getAmount() - 1);
        }
    }

    public void removeProduct(Vehicle v) {
        lista_samochodow.remove(v);
    }

    public Vehicle search(String s) {
        for (int i = 0; i < lista_samochodow.size(); i++) {
            if (lista_samochodow.get(i).getModel().equals(s)) {
                return lista_samochodow.get(i);
            }
        }
        return null;
    }

    public void searchPartial(String s) {
        String temp;
        for (int i = 0; i < lista_samochodow.size(); i++) {
            temp = lista_samochodow.get(i).getBrand() + " " + lista_samochodow.get(i).getModel();
            if (temp.contains(s)) {
                System.out.println(lista_samochodow.get(i));
            }

        }
    }

    public int countByCondition(itemCondi ic) {
        int temp = 0;
        for (int i = 0; i < lista_samochodow.size(); i++) {
            if (lista_samochodow.get(i).getCondition() == ic) {
                temp++;
            }
        }
        return temp;
    }

    public void summary() {
        for (int i = 0; i < lista_samochodow.size(); i++) {
            System.out.println(lista_samochodow.get(i));
        }
    }

    public void sortByName() {
        Collections.sort(lista_samochodow);
    }

    public void sortByAmount() {
        Collections.sort(lista_samochodow, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                int result = Integer.compare(o1.getAmount(), o2.getAmount());
                return result;
            }
        });
    }

    public Vehicle max() {
        return Collections.max(lista_samochodow, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                int result = Integer.compare(o1.getAmount(), o2.getAmount());
                return result;
            }
        });
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Vehicle> getLista_samochodow() {
        return lista_samochodow;
    }

    public void setLista_samochodow(List<Vehicle> lista_samochodow) {
        this.lista_samochodow = lista_samochodow;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    @Override
    public String toString() {
        return "CarShowroom{" +
                "nazwa='" + nazwa + '\'' +
                ", pojemnosc=" + pojemnosc +
                '}';
    }
}
