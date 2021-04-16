package com.company.modelEntities.CarShowroomContainer;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CarShowroomContainerService implements ICarShowroomContainerService {
    CarshowroomcontainerEntity e;
    final Session session;

    public CarShowroomContainerService(Session session, CarshowroomcontainerEntity e) {
        this.e = e;
        this.session = session;
    }

    @Override
    public void addCenter(String s, int d) {
        session.beginTransaction();
        CarshowroomEntity n = new CarshowroomEntity();
        n.setCapacity(d);
        n.setName(s);
        n.setCarshowroomcontainerentity(e);
        session.save(n);
        session.getTransaction().commit();
    }

    @Override
    public void removeCenter(String s) {
        session.beginTransaction();
        for (int i = 0; i < e.getSalons().size(); i++) {
            if (e.getSalons().get(i).getName().equals(s)) {
                session.remove(e.getSalons().get(i));
            }
        }
        session.getTransaction().commit();
        session.refresh(e);
    }

    @Override
    public List<CarshowroomEntity> findEmpty() {
        String hql = "SELECT * FROM carshowroom";
        Query query = session.createSQLQuery(hql).addEntity(CarshowroomEntity.class);
        List<CarshowroomEntity> l = query.list();
        List<CarshowroomEntity> result = new ArrayList<>();
        for (CarshowroomEntity res : l) {
            if (res.getVehicles().size() == 0) {
                result.add(res);
            }
        }
        return result;
    }

    @Override
    public void summary() {
        float pojemnosc;
        float size;
        for (CarshowroomEntity s : e.getSalons()) {
            pojemnosc = s.getCapacity();
            size = s.getVehicles().size();
            System.out.println(s.getName() + ", " + (size / pojemnosc) * 100 + "%");
        }
    }

    @Override
    public List<CarshowroomEntity> sortByCapacity() {
        String hql = "SELECT * FROM carshowroom ORDER BY capacity";
        Query query = session.createSQLQuery(hql).addEntity(CarshowroomEntity.class);
        return query.list();
    }

    @Override
    public Object getSalonByName(String name) {
        Query query = session.createSQLQuery("Select * FROM carshowroom WHERE name=:name").addEntity(CarshowroomEntity.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
