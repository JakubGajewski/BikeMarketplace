package pl.jakubgajewski.BikeMarketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jakubgajewski.BikeMarketplace.models.repositories.AccessoryRepo;
import pl.jakubgajewski.BikeMarketplace.models.repositories.BikeRepo;
import pl.jakubgajewski.BikeMarketplace.models.repositories.UserRepo;
import pl.jakubgajewski.BikeMarketplace.models.services.UserService;

@Controller
public class MainController {

    final
    BikeRepo bikeRepo;

    final
    AccessoryRepo accessoryRepo;

    final
    UserRepo userRepo;

    final
    UserService userService;

    @Autowired
    public MainController (BikeRepo bikeRepo, AccessoryRepo accessoryRepo, UserRepo userRepo, UserService userService) {
        this.bikeRepo = bikeRepo;
        this.accessoryRepo = accessoryRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("userLogin", userService.getCurrentUser().getLogin());
        return "dashboard";
    }
    //TODO: implement
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    //TODO: implement
    @GetMapping("/newad")
    public String newAd(){
        return "newad";
    }
}

