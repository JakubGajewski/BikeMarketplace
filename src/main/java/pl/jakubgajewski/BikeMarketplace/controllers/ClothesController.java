package pl.jakubgajewski.BikeMarketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jakubgajewski.BikeMarketplace.models.repositories.BikeRepo;
import pl.jakubgajewski.BikeMarketplace.models.repositories.UserRepo;
import pl.jakubgajewski.BikeMarketplace.models.services.UserService;

@Controller
public class ClothesController {

    final
    BikeRepo bikeRepo;

    final
    UserRepo userRepo;

    final
    UserService userService;

    @Autowired
    public ClothesController(BikeRepo bikeRepo, UserRepo userRepo, UserService userService) {
        this.bikeRepo = bikeRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/clothes")
    public String clothes(){
        return "clothes";
    }

}

