package com.sda.carrentalservice.repository;

import com.sda.carrentalservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("From Car car where lower(car.make) like '%:carName%' or lower(car.model) like '%:carName%'")
    Car findCarByName(@Param("carName") String carName);
}
