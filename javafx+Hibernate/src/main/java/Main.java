import com.company.CarShowroomContainer;
import com.company.modelEntities.CarShowroom.CarShowroomService;
import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.company.modelEntities.CarShowroom.ICarShowroomService;
import com.company.modelEntities.CarShowroomContainer.CarShowroomContainerService;
import com.company.modelEntities.CarShowroomContainer.CarshowroomcontainerEntity;
import com.company.modelEntities.CarShowroomContainer.ICarShowroomContainerService;
import com.company.modelEntities.Vehicle.VehicleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.List;
import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            ICarShowroomContainerService c = new CarShowroomContainerService(session, (CarshowroomcontainerEntity) session.get(CarshowroomcontainerEntity.class, 1));
            c.addCenter("salontodel", 0);
            c.removeCenter("salontodel");

            List empty = c.findEmpty();
            List sortbycapacity = c.sortByCapacity();
            c.summary();

            ICarShowroomService s = new CarShowroomService(session, (CarshowroomEntity) session.get(CarshowroomEntity.class, 6));
            VehicleEntity ve = new VehicleEntity();
            ve.setBrand("mazda");
            ve.setModel("d");
            ve.setPrice(3);
            ve.setYearOfProduction(2019);
            ve.setMileage(0);
            ve.setEngineCapacity(2000);
            ve.setAmount(1);
            ve.setPhoneNumber(8888);
            ve.setFuel("gasoline");
            ve.setReserved((byte) 0);

            s.addProduct(ve);
            int a = ((VehicleEntity) session.createSQLQuery("SELECT * FROM vehicle ORDER BY id DESC LIMIT 1 ").addEntity(VehicleEntity.class).getSingleResult()).getId();
            s.removeProduct((VehicleEntity) session.get(VehicleEntity.class, a));


            List sortbyname = s.sortByName();
            List search = s.search("ford");
            List max = s.max();
            System.out.println();
//        session.beginTransaction();
//        session.save(ve);
//        session.getTransaction().commit();


        } finally {
            session.close();
        }
    }
}