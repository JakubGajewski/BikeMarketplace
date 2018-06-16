package pl.jakubgajewski.GieldaRowerowa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jakubgajewski.GieldaRowerowa.models.repositories.BikeRepo;
import pl.jakubgajewski.GieldaRowerowa.models.repositories.UserRepo;
import pl.jakubgajewski.GieldaRowerowa.models.services.UserService;

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

