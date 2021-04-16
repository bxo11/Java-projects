package com.company.modelEntities.CarShowroomContainer;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;

import java.util.*;

import javax.persistence.*;
import javax.swing.*;

@Entity
@Table(name = "carshowroomcontainer", schema = "javahibernate", catalog = "")
public class CarshowroomcontainerEntity {
    private int id;
    private String name;

    private List<CarshowroomEntity> salons = new ArrayList<>();

    @OneToMany(mappedBy = "carshowroomcontainerentity")
    public List<CarshowroomEntity> getSalons() {
        return salons;
    }

    public void setSalons(List<CarshowroomEntity> salons) {
        this.salons = salons;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarshowroomcontainerEntity that = (CarshowroomcontainerEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
