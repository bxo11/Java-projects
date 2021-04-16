package com.company.modelEntities.CarShowroomContainer;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;

import java.util.*;

public interface ICarShowroomContainerService {

    public void addCenter(String s, int d);

    public void removeCenter(String s);

    public List<CarshowroomEntity> findEmpty();

    public void summary();

    public List<CarshowroomEntity> sortByCapacity();

    public Object getSalonByName(String name);

}
