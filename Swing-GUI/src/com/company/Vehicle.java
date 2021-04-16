package com.company;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {
    private String marka;
    private String model;
    private itemCondition stan;
    private double cena;
    private int rok_produkcji;
    private double przebieg;
    private double pojemnosc_silnika;
    private int ilosc;

    public Vehicle(String marka, String model, itemCondition stan, double cena, int rok_produkcji, double przebieg, double pojemnosc_silnika, int ilosc) {
        this.marka = marka;
        this.model = model;
        this.stan = stan;
        this.cena = cena;
        this.rok_produkcji = rok_produkcji;
        this.przebieg = przebieg;
        this.pojemnosc_silnika = pojemnosc_silnika;
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", stan=" + stan +
                ", cena=" + cena +
                ", rok_produkcji=" + rok_produkcji +
                ", przebieg=" + przebieg +
                ", pojemnosc_silnika=" + pojemnosc_silnika +
                ", ilosc=" + ilosc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.cena, cena) == 0 &&
                rok_produkcji == vehicle.rok_produkcji &&
                Double.compare(vehicle.przebieg, przebieg) == 0 &&
                Double.compare(vehicle.pojemnosc_silnika, pojemnosc_silnika) == 0 &&
                ilosc == vehicle.ilosc &&
                Objects.equals(marka, vehicle.marka) &&
                Objects.equals(model, vehicle.model) &&
                stan == vehicle.stan;
    }

    @Override
    public int hashCode() {
        return Objects.hash(marka, model, stan, cena, rok_produkcji, przebieg, pojemnosc_silnika, ilosc);
    }

    @Override
    public int compareTo(Vehicle o) {
        int result = this.getModel().compareTo(o.getModel());
        return result;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public itemCondition getStan() {
        return stan;
    }

    public void setStan(itemCondition stan) {
        this.stan = stan;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getRok_produkcji() {
        return rok_produkcji;
    }

    public void setRok_produkcji(int rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

    public double getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(double przebieg) {
        this.przebieg = przebieg;
    }

    public double getPojemnosc_silnika() {
        return pojemnosc_silnika;
    }

    public void setPojemnosc_silnika(double pojemnosc_silnika) {
        this.pojemnosc_silnika = pojemnosc_silnika;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
