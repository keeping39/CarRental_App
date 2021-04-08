package com.carrentalservice.DTO;

import java.util.List;

public class BranchDTO extends BaseEntityDTO {

    private String name;
    private String address;
    private List<CarDTO> carsDTO;

    public BranchDTO(Long id, String name, String address, List<CarDTO> carsDTO) {
        super(id);
        this.name = name;
        this.address = address;
        this.carsDTO = carsDTO;
    }

    public BranchDTO() {
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

    public List<CarDTO> getCarsDTO() {
        return carsDTO;
    }

    public void setCarsDTO(List<CarDTO> cars) {
        this.carsDTO = carsDTO;
    }
}
