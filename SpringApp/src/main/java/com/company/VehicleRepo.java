package com.company;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.company.modelEntities.Vehicle.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleRepo extends JpaRepository<VehicleEntity, Long> {

    @Query("SELECT v FROM VehicleEntity v WHERE id_salon=:a_id")
    Optional<List<VehicleEntity>> findAllInSalon(@Param("a_id") String id);

    VehicleEntity findByBrand(String brand);
   // List<VehicleEntity> findAllInSalon(@Param("a_id") String id);

}
