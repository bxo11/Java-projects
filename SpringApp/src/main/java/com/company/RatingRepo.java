package com.company;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.company.modelEntities.Rating.RatingEntity;
import com.company.modelEntities.Vehicle.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepo extends JpaRepository<RatingEntity, Long> {

    @Query("SELECT r FROM RatingEntity r WHERE id_salon=:a_id")
    Optional<List<RatingEntity>> findAllInSalon(@Param("a_id") String id);

}

