package com.carrentalservice.restcontroller;

import com.carrentalservice.DTO.CustomerDTO;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.service.CustomerService;
import com.carrentalservice.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerTransformer customerTransformer;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerTransformer customerTransformer) {
        this.customerService = customerService;
        this.customerTransformer = customerTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        CustomerDTO customerDTO = customerTransformer.transformFromEntityToDTO(customer);
        return ResponseEntity.ok(customerDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerTransformer.transformFromDTOToEntity(customerDTO);
        Customer saveCustomer = customerService.saveCustomer(customer);
        CustomerDTO savedCustomerDTO = customerTransformer.transformFromEntityToDTO(saveCustomer);
        return ResponseEntity.ok(savedCustomerDTO);
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerTransformer.transformFromDTOToEntity(customerDTO);
        Customer saveCustomer = customerService.saveCustomer(customer);
        CustomerDTO savedCustomerDTO = customerTransformer.transformFromEntityToDTO(saveCustomer);
        return ResponseEntity.ok(savedCustomerDTO);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> listAllCustomer(){
        List<Customer> allCustomer = customerService.findAllCustomer();
        List<CustomerDTO> allCustomerDTO = new ArrayList<>();

        for (Customer customer: allCustomer){
            allCustomerDTO.add(customerTransformer.transformFromEntityToDTO(customer));
        }
        return ResponseEntity.ok(allCustomerDTO);
    }
}
