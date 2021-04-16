package com.company.modelEntities.Vehicle;

import com.company.ToCSV;
import com.company.itemCondi;
import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vehicle", schema = "javahibernate", catalog = "")
public class VehicleEntity implements Serializable, Comparable<VehicleEntity> {
    private long id;
    @ToCSV
    private String brand;
    @ToCSV
    private String model;
    @ToCSV
    private double price;
    @ToCSV
    private int yearOfProduction;
    @ToCSV
    private double mileage;
    @ToCSV
    private double engineCapacity;
    @ToCSV
    private int amount;
    @ToCSV
    private int phoneNumber;
    @ToCSV
    private String fuel;
    @ToCSV
    private String salonName;
    private byte reserved;

    @JsonIgnoreProperties("vehicles")
    private CarshowroomEntity r_salon;

    @ManyToOne
    @JoinColumn(name = "id_salon", nullable = false)
    public CarshowroomEntity getR_salon() {
        return r_salon;
    }

    public void setR_salon(CarshowroomEntity r_salon) {
        this.r_salon = r_salon;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "year_of_production")
    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Basic
    @Column(name = "mileage")
    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    @Basic
    @Column(name = "engine_capacity")
    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "phone_number")
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "fuel")
    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @Basic
    @Column(name = "salon_name")
    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    @Basic
    @Column(name = "reserved")
    public byte getReserved() {
        return reserved;
    }

    public void setReserved(byte reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleEntity that = (VehicleEntity) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                yearOfProduction == that.yearOfProduction &&
                Double.compare(that.mileage, mileage) == 0 &&
                Double.compare(that.engineCapacity, engineCapacity) == 0 &&
                amount == that.amount &&
                phoneNumber == that.phoneNumber &&
                reserved == that.reserved &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model) &&
                Objects.equals(fuel, that.fuel) &&
                Objects.equals(salonName, that.salonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, price, yearOfProduction, mileage, engineCapacity, amount, phoneNumber, fuel, salonName, reserved);
    }

    @Override
    public int compareTo(VehicleEntity o) {
        int result = this.getModel().compareTo(o.getModel());
        return result;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", yearOfProduction=" + yearOfProduction +
                ", mileage=" + mileage +
                ", engineCapacity=" + engineCapacity +
                ", amount=" + amount +
                ", phoneNumber=" + phoneNumber +
                ", fuel='" + fuel + '\'' +
                ", salonName='" + salonName + '\'' +
                ", reserved=" + reserved +
                '}';
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        this.brand = aInputStream.readUTF();
        this.model = aInputStream.readUTF();
        this.price = aInputStream.readDouble();
        this.yearOfProduction = aInputStream.readInt();
        this.mileage = aInputStream.readDouble();
        this.engineCapacity = aInputStream.readDouble();
        this.amount = aInputStream.readInt();
        this.salonName = aInputStream.readUTF();
        this.reserved = aInputStream.readByte();
        this.phoneNumber = aInputStream.readInt();
        this.fuel = aInputStream.readUTF();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeUTF(brand);
        aOutputStream.writeUTF(model);
        aOutputStream.writeDouble(price);
        aOutputStream.writeInt(yearOfProduction);
        aOutputStream.writeDouble(mileage);
        aOutputStream.writeDouble(engineCapacity);
        aOutputStream.writeInt(amount);
        aOutputStream.writeUTF(salonName);
        aOutputStream.writeByte(reserved);
        aOutputStream.writeInt(phoneNumber);
        aOutputStream.writeUTF(fuel);
    }
}
