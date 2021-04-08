package com.carrentalservice.DTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO extends UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String username, String password, String confirmPassword, String firstName, String lastName, String email, String address) {
        super(id, username, password, confirmPassword);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}