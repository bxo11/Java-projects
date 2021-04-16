package com.company.modelEntities.CarShowroom;

import com.company.modelEntities.CarShowroomContainer.CarshowroomcontainerEntity;
import com.company.modelEntities.Rating.RatingEntity;
import com.company.modelEntities.Vehicle.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carshowroom", schema = "javahibernate", catalog = "")
public class CarshowroomEntity {
    private long id;
    private String name;
    private int capacity;
    @JsonIgnoreProperties("salons")
    private CarshowroomcontainerEntity carshowroomcontainerentity;
    @JsonIgnoreProperties("r_salon")
    private List<VehicleEntity> vehicles = new ArrayList<>();
    @JsonIgnoreProperties("r_salon")
    private List<RatingEntity> ratings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_container", nullable = false)
    public CarshowroomcontainerEntity getCarshowroomcontainerentity() {
        return carshowroomcontainerentity;
    }


    public void setCarshowroomcontainerentity(CarshowroomcontainerEntity carshowroomcontainerentity) {
        this.carshowroomcontainerentity = carshowroomcontainerentity;
    }

    @OneToMany(mappedBy = "r_salon")
    public List<VehicleEntity> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleEntity> vehicles) {
        this.vehicles = vehicles;
    }

    @OneToMany(mappedBy = "r_salon")
    public List<RatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingEntity> ratings) {
        this.ratings = ratings;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarshowroomEntity that = (CarshowroomEntity) o;
        return id == that.id &&
                capacity == that.capacity &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity);
    }
}
