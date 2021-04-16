package com.company;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.company.modelEntities.CarShowroomContainer.CarshowroomcontainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarShowroomRepo extends JpaRepository<CarshowroomEntity, Long> {

    @Query("SELECT s FROM CarshowroomEntity s WHERE s.id=:a_id")
    Optional<CarshowroomEntity> selectCapacity(@Param("a_id") String p_id);

    CarshowroomEntity findByName(String name);

    //last id
    //CarshowroomEntity findTopByOrderByIdDesc(String name);

    //CarshowroomEntity selectCapacity(@Param("a_id") String p_id);
}
