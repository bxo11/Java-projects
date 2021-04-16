package com.company.modelEntities.CarShowroomContainer;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

import javax.persistence.*;
import javax.swing.*;

@Entity
@Table(name = "carshowroomcontainer", schema = "javahibernate", catalog = "")
public class CarshowroomcontainerEntity {
    private long id;
    private String name;

    @JsonIgnoreProperties("carshowroomcontainerentity")
    private List<CarshowroomEntity> salons = new ArrayList<>();

    @OneToMany(mappedBy = "carshowroomcontainerentity", fetch = FetchType.EAGER)
    public List<CarshowroomEntity> getSalons() {
        return salons;
    }

    public void setSalons(List<CarshowroomEntity> salons) {
        this.salons = salons;
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
