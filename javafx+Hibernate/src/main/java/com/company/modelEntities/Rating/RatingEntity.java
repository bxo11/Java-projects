package com.company.modelEntities.Rating;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rating", schema = "javahibernate", catalog = "")
public class RatingEntity {
    private int id;
    private int value;
    private Timestamp date;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingEntity that = (RatingEntity) o;
        return id == that.id &&
                value == that.value &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, date);
    }
}
