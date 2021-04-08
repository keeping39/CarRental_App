package com.carrentalservice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Branch extends BaseEntity {

    private String name;

    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;

    public Branch(String name, String address, List<Car> cars) {
        this.name = name;
        this.address = address;
        this.cars = cars;
    }

    public Branch() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
