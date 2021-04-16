package com.company.modelEntities.CarShowroom;

import com.company.modelEntities.Rating.RatingEntity;
import com.company.modelEntities.Vehicle.VehicleEntity;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CarShowroomService implements ICarShowroomService {
    CarshowroomEntity e;
    private final Session session;

    public CarShowroomService(Session session, CarshowroomEntity e) {
        this.e = e;
        this.session = session;
    }

    @Override
    public void addProduct(VehicleEntity v) {
        session.beginTransaction();
        if (e.getVehicles().size() < e.getCapacity()) {
            v.setSalonName(e.getName());
            v.setR_salon(e);
            session.save(v);
            session.save(e);
        } else {
            System.err.println("max limit");
        }
        session.refresh(e);
        session.getTransaction().commit();
    }

    @Override
    public void removeProduct(VehicleEntity v) {
        session.beginTransaction();
        session.delete(v);
        session.getTransaction().commit();
        session.refresh(e);
    }

    @Override
    public List sortByName() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<VehicleEntity> cr = cb.createQuery(VehicleEntity.class);
        Root<VehicleEntity> root = cr.from(VehicleEntity.class);
        cr.select(root).where(cb.equal(root.get("r_salon"), e.getId())).orderBy(cb.asc(root.get("brand")));
        Query<VehicleEntity> query = session.createQuery(cr);

//        Query query = session.createSQLQuery("SELECT * FROM vehicle WHERE id_salon=:id_s ORDER BY brand").addEntity(VehicleEntity.class);
//        query.setParameter("id_s",e.getId());
        return query.getResultList();
    }

    @Override
    public List search(String name) {
        Query query = session.createSQLQuery("SELECT * FROM vehicle WHERE brand=:brand_s AND id_salon=:id_s ORDER BY model").addEntity(VehicleEntity.class);
        query.setParameter("brand_s", name);
        query.setParameter("id_s", e.getId());
        return query.list();
    }

    @Override
    public List max() {
        Query query = session.createSQLQuery("SELECT * FROM vehicle WHERE id_salon=:id_s ORDER BY amount DESC LIMIT 1").addEntity(VehicleEntity.class);
        query.setParameter("id_s", e.getId());
        return query.list();
    }
}
