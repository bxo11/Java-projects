package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

public class Vehicle implements Comparable<Vehicle>, Serializable {
    @ToCSV
    private String brand;
    @ToCSV
    private String model;
    private itemCondi condition;
    @ToCSV
    private double price;
    @ToCSV
    private int year_of_production;
    @ToCSV
    private double mileage;
    @ToCSV
    private double engine_capacity;
    @ToCSV
    private int amount;
    @ToCSV
    private int phoneNumber;
    @ToCSV
    private String fuel;
    @ToCSV
    private String salonName;
    private boolean reserved;

    public Vehicle(String brand, String model, itemCondi condition, double price, int year_of_production, double mileage, double engine_capacity, int amount, int phoneNumber, String fuel) {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.price = price;
        this.year_of_production = year_of_production;
        this.mileage = mileage;
        this.engine_capacity = engine_capacity;
        this.amount = amount;
        this.phoneNumber = phoneNumber;
        this.fuel = fuel;
        this.salonName = "";
        this.reserved = false;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", condition=" + condition +
                ", price=" + price +
                ", year_of_production=" + year_of_production +
                ", mileage=" + mileage +
                ", engine_capacity=" + engine_capacity +
                ", amount=" + amount +
                ", salonName='" + salonName + '\'' +
                ", reserved=" + reserved +
                ", phoneNumber=" + phoneNumber +
                ", fuel='" + fuel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.price, price) == 0 &&
                year_of_production == vehicle.year_of_production &&
                Double.compare(vehicle.mileage, mileage) == 0 &&
                Double.compare(vehicle.engine_capacity, engine_capacity) == 0 &&
                amount == vehicle.amount &&
                phoneNumber == vehicle.phoneNumber &&
                brand.equals(vehicle.brand) &&
                model.equals(vehicle.model) &&
                condition == vehicle.condition &&
                salonName.equals(vehicle.salonName) &&
                fuel.equals(vehicle.fuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, condition, price, year_of_production, mileage, engine_capacity, amount, salonName, fuel);
    }

    @Override
    public int compareTo(Vehicle o) {
        int result = this.getModel().compareTo(o.getModel());
        return result;
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        this.brand = aInputStream.readUTF();
        this.model = aInputStream.readUTF();
        this.condition = itemCondi.NEW;
        this.price = aInputStream.readDouble();
        this.year_of_production = aInputStream.readInt();
        this.mileage = aInputStream.readDouble();
        this.engine_capacity = aInputStream.readDouble();
        this.amount = aInputStream.readInt();
        this.salonName = aInputStream.readUTF();
        this.reserved = aInputStream.readBoolean();
        this.phoneNumber = aInputStream.readInt();
        this.fuel = aInputStream.readUTF();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeUTF(brand);
        aOutputStream.writeUTF(model);
        aOutputStream.writeDouble(price);
        aOutputStream.writeInt(year_of_production);
        aOutputStream.writeDouble(mileage);
        aOutputStream.writeDouble(engine_capacity);
        aOutputStream.writeInt(amount);
        aOutputStream.writeUTF(salonName);
        aOutputStream.writeBoolean(reserved);
        aOutputStream.writeInt(phoneNumber);
        aOutputStream.writeUTF(fuel);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public itemCondi getCondition() {
        return condition;
    }

    public void setCondition(itemCondi condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear_of_production() {
        return year_of_production;
    }

    public void setYear_of_production(int year_of_production) {
        this.year_of_production = year_of_production;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(double engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
