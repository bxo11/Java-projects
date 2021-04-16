package com.company.modelEntities.CarShowroom;

import com.company.modelEntities.Vehicle.VehicleEntity;

import java.util.List;

public interface ICarShowroomService {
    public void addProduct(VehicleEntity v);

    public void removeProduct(VehicleEntity v);

    public List sortByName();

    public List search(String name);

    public List max();

}
