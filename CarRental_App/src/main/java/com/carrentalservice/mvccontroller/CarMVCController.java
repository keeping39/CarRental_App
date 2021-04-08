package com.carrentalservice.mvccontroller;

import com.carrentalservice.entity.Car;
import com.carrentalservice.service.BranchService;
import com.carrentalservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CarMVCController {

    private final CarService carService;
    private final BranchService branchService;

    @Autowired
    public CarMVCController(CarService carService, BranchService branchService) {
        this.carService = carService;
        this.branchService = branchService;
    }

    @GetMapping(path = "/cars")
    public String showCars(Model model) {
        model.addAttribute("cars", this.carService.findAllCars());
        model.addAttribute("carsNumber", this.carService.countCars());
        return "car-list";
    }

    @GetMapping(path = "/car/registration")
    public String showRegistration(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        return "add-car";
    }

    @PostMapping(path = "/car/add")
    public String addCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-car";
        } else {
            car.setBranch(branchService.findBranchById(car.getBranch().getId()));
            this.carService.saveCar(car);
            return "redirect:/cars";
        }
    }

    @GetMapping(path = "/car/delete/{id}")
    public String deleteCarById(@PathVariable("id") Long id) {
        this.carService.deleteCarById(id);
        return "redirect:/cars";
    }

    @PostMapping(path = "/car/update")
    public String editCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-car";
        }
        car.setBranch(branchService.findBranchById(car.getBranch().getId()));
        this.carService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping(path = "/car/edit/{id}")
    public String showEditPageCar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("car", this.carService.findCarById(id));
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        return "edit-car";
    }
}
