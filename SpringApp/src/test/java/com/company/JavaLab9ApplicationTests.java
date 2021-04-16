package com.company;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.company.modelEntities.CarShowroomContainer.CarshowroomcontainerEntity;
import com.company.modelEntities.Rating.RatingEntity;
import com.company.modelEntities.Vehicle.VehicleEntity;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class JavaLab9ApplicationTests {


    @Autowired
    private RESTController restController;
    @Autowired
    private CarShowroomContainerRepo carShowroomContainerRepo;
    @Autowired
    private CarShowroomRepo carShowroomRepo;
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    int randomServerPort;

    @Test
    @Order(1)
    void createCarshowroomTest() throws URISyntaxException {
        CarshowroomEntity s = new CarshowroomEntity();
        CarshowroomcontainerEntity cFromDB = carShowroomContainerRepo.findById((long) 1).orElseThrow();
        s.setName("test salon");
        s.setCapacity(101);
        s.setCarshowroomcontainerentity(cFromDB);

        final String baseUrl = "http://localhost:"+randomServerPort+"/api/fulfillment/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<CarshowroomEntity> request = new HttpEntity<>(s, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    @Order(2)
    void createVehicleTest() throws URISyntaxException {
        CarshowroomEntity sFromDB = carShowroomRepo.findByName("test salon");
        VehicleEntity v = new VehicleEntity();
        v.setBrand("test");
        v.setModel("test");
        v.setPrice(1.1);
        v.setYearOfProduction(10001);
        v.setMileage(1.2);
        v.setEngineCapacity(1.3);
        v.setAmount(1);
        v.setPhoneNumber(1111);
        v.setFuel("test");
        v.setSalonName(sFromDB.getName());
        v.setR_salon(sFromDB);
        v.setReserved((byte)0);

        final String baseUrl = "http://localhost:"+randomServerPort+"/api/product/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<VehicleEntity> request = new HttpEntity<>(v, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    @Order(3)
    void getCSVTest() {
        String result = restController.getCSV();
        assertNotNull(result);
    }

    @Test
    @Order(4)
    void createRatingTest() throws URISyntaxException {
        CarshowroomEntity sFromDB = carShowroomRepo.findByName("test salon");
        RatingEntity r = new RatingEntity();

        r.setValue(4);
        r.setR_salon(sFromDB);

        final String baseUrl = "http://localhost:"+randomServerPort+"/api/rating/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<RatingEntity> request = new HttpEntity<>(r, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }

//    @Test
//    @Order(1)
//    void createTest() {
//
//        CarshowroomEntity s = new CarshowroomEntity();
//        CarshowroomcontainerEntity cFromDB = carShowroomContainerRepo.findById((long) 1).orElseThrow();
//        s.setName("test salon");
//        s.setCapacity(101);
//        s.setCarshowroomcontainerentity(cFromDB);
//
//        carShowroomRepo.save(s);
//        CarshowroomEntity sFromDB = carShowroomRepo.findByName("test salon");
//        assertEquals(s.getName(),sFromDB.getName());
//
//        CarshowroomEntity sFromDB = carShowroomRepo.findByName("test salon");
//        VehicleEntity v = new VehicleEntity();
//        v.setBrand("test");
//        v.setModel("test");
//        v.setPrice(1.1);
//        v.setYearOfProduction(10001);
//        v.setMileage(1.2);
//        v.setEngineCapacity(1.3);
//        v.setAmount(1);
//        v.setPhoneNumber(1111);
//        v.setFuel("test");
//        v.setSalonName(sFromDB.getName());
//        v.setR_salon(sFromDB);
//        v.setReserved((byte)0);
//        vehicleRepo.save(v);
//        VehicleEntity vFromDB = vehicleRepo.findByBrand("test");
//        assertEquals(v.getBrand(),vFromDB.getBrand());
//
//        RatingEntity r = new RatingEntity();
//        RatingEntity r1 = new RatingEntity();
//
//        r.setValue(5);
//        r.setR_salon(sFromDB);
//
//        r1.setValue(3);
//        r1.setR_salon(sFromDB);
//
//        ratingRepo.save(r);
//        ratingRepo.save(r1);
//        assertEquals(r.getR_salon().getId(),sFromDB.getId());
//
//
//    }

    @Test
    @Order(5)
    void getAverageRatingTest() {
        CarshowroomEntity s = carShowroomRepo.findByName("salon2");
        double avg = restController.getAverageRating(s.getId());

        assertEquals(avg,3.5);
    }

    @Test
    @Order(6)
    void deleteVehicleTest() {
        VehicleEntity vFromDB = vehicleRepo.findByBrand("test");
        restController.deleteVehicle(vFromDB.getId());
        try {
            VehicleEntity vFromDB2 = vehicleRepo.findByBrand("test");
        }catch (NullPointerException e) {
            assertEquals(e, new NullPointerException());
        }
    }

    @Test
    @Order(7)
    void deleteCarshowroomTest() {
        CarshowroomEntity sFromDB = carShowroomRepo.findByName("test salon");
        restController.deleteSalon(sFromDB.getId());
        //carShowroomRepo.delete(sFromDB);
        try {
            CarshowroomEntity sFromDB2 = carShowroomRepo.findByName("test salon");
        }catch (NullPointerException e) {
            assertEquals(e, new NullPointerException());
        }
    }

}
