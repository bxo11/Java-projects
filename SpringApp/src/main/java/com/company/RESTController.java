package com.company;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.company.modelEntities.Rating.RatingEntity;
import com.company.modelEntities.Vehicle.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private CarShowroomRepo carShowroomRepo;
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private RatingRepo ratingRepo;

    //1
    @PostMapping("/product")
    public VehicleEntity createVehicle(@RequestBody VehicleEntity vehicle){
        return this.vehicleRepo.save(vehicle);
    }

    //2
    @DeleteMapping("/product/{id}")
    public ResponseEntity<VehicleEntity> deleteVehicle(@PathVariable ("id") long vehicleID){
        VehicleEntity existingVehicle = this.vehicleRepo.findById(vehicleID).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id:" + vehicleID));
        this.vehicleRepo.delete(existingVehicle);
        return ResponseEntity.ok().build();
    }

    //4
    @GetMapping("/product/csv")
    public String getCSV(){
        List<VehicleEntity> a = this.vehicleRepo.findAll();
        if(a.size() ==0 ){
            new ResourceNotFoundException("Resourced not found");
        }

        String result = "";
        Field[] fields = VehicleEntity.class.getDeclaredFields();

        for (VehicleEntity v : a) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(ToCSV.class)) {
                    try {
                        field.setAccessible(true);
                        result +=String.valueOf(field.get(v))+", ";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            result +="\n";
        }


        return result;
    }

    //3
    @GetMapping("/fulfillment/{id}/rating")
    public double getAverageRating(@PathVariable ("id") long salonID){
        List<RatingEntity> temp_l = this.ratingRepo.findAllInSalon(String.valueOf(salonID)).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id:" + salonID));
        double result =0;
        for(RatingEntity x : temp_l){
            result+= x.getValue();
        }
        return result / temp_l.size();
    }

    //5
    @GetMapping("/fulfillment")
    public List<CarshowroomEntity> getAllSalons(){
        return this.carShowroomRepo.findAll();
    }

    //6
    @PostMapping("/fulfillment")
    public CarshowroomEntity createSalon(@RequestBody CarshowroomEntity salon){
        return this.carShowroomRepo.save(salon);
    }

    //7
    @DeleteMapping("/fulfillment/{id}")
    public ResponseEntity<CarshowroomEntity> deleteSalon(@PathVariable ("id") long salonID){
        CarshowroomEntity existingSalon = this.carShowroomRepo.findById(salonID).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id:" + salonID));
        this.carShowroomRepo.delete(existingSalon);
        return ResponseEntity.ok().build();
    }

    //8
    @GetMapping("/fulfillment/{id}/products")
    public List<VehicleEntity> getAllVehiclesIn(@PathVariable ("id") String salonID){
        List<VehicleEntity> result = this.vehicleRepo.findAllInSalon(salonID).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id:" + salonID));
        return result;
    }

    //9
    @GetMapping("/fulfillment/{id}/fill")
    public double getAllSalons(@PathVariable ("id") long salonID){
        List<VehicleEntity> temp_l = this.vehicleRepo.findAllInSalon(String.valueOf(salonID)).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id:" + salonID));
        CarshowroomEntity salon =  this.carShowroomRepo.findById(salonID).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id:" + salonID));
        double result = (double)temp_l.size()/salon.getCapacity()*100;
        return result;
    }

    //10
    @PostMapping("/rating")
    public RatingEntity createRating(@RequestBody RatingEntity rating){
        return this.ratingRepo.save(rating);
    }

}
