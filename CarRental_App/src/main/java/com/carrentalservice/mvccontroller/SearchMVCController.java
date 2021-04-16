package com.carrentalservice.mvccontroller;

import com.carrentalservice.DTO.SearchValueDTO;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchMVCController {

    private final BookingService bookingService;
    private final BranchService branchService;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final RentalOfficeService rentalOfficeService;
    private final RentalService rentalService;
    private final ReturnCarService returnCarService;
    private final RevenueService revenueService;

    @Autowired
    public SearchMVCController(BookingService bookingService, BranchService branchService, CarService carService, CustomerService customerService, EmployeeService employeeService, RentalService rentalService, RentalOfficeService rentalOfficeService, ReturnCarService returnCarService, RevenueService revenueService) {
        this.bookingService = bookingService;
        this.branchService = branchService;
        this.carService = carService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.rentalService = rentalService;
        this.rentalOfficeService = rentalOfficeService;
        this.returnCarService = returnCarService;
        this.revenueService = revenueService;
    }

    @PostMapping(path = "/search")
    public String search(@ModelAttribute("search") String searchString, Double search, Model model, BindingResult bindingResult) {
        model.addAttribute("bookings", this.bookingService.findBookingByName(searchString));
        model.addAttribute("branches", this.branchService.findBranchByName(searchString));
        model.addAttribute("cars", this.carService.findCarByName(searchString));
        model.addAttribute("customers", this.customerService.findCustomerByName(searchString));
        model.addAttribute("employees", this.employeeService.findEmployeeByName(searchString));
        model.addAttribute("rentals", this.rentalService.findRentalByName(searchString));
        model.addAttribute("rentalOffices", this.rentalOfficeService.findRentalOfficeByName(searchString));
        model.addAttribute("returnCars", this.returnCarService.findReturnCarByName(searchString));
        model.addAttribute("revenues", this.revenueService.findRevenueByDetails(search));
        ObjectError error = new ObjectError("search", "Nothing found!");
        bindingResult.addError(error);
        return "index";
    }

    @GetMapping(path = "/")
    public String showSearch(Model model) {
        model.addAttribute("search", new SearchValueDTO());
        model.addAttribute("booking", new Booking());
        model.addAttribute("allCars", this.carService.findAllCars());
        return "index";
    }
}
