package pl.jakubgajewski.GieldaRowerowa.controllers;

import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jakubgajewski.GieldaRowerowa.models.BikeModel;
import pl.jakubgajewski.GieldaRowerowa.models.UserModel;
import pl.jakubgajewski.GieldaRowerowa.models.forms.BikeForm;
import pl.jakubgajewski.GieldaRowerowa.models.repositories.BikeRepo;
import pl.jakubgajewski.GieldaRowerowa.models.repositories.UserRepo;
import pl.jakubgajewski.GieldaRowerowa.services.UserService;

@Controller
public class MainController {

    final
    BikeRepo bikeRepo;

    final
    UserRepo userRepo;

    final
    UserService userService;

    @Autowired
    public MainController (BikeRepo bikeRepo, UserRepo userRepo, UserService userService) {
        this.bikeRepo = bikeRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("userLogin", userService.getCurrentUser().getLogin());
        return "dashboard";
    }

    @GetMapping("/bikes")
    public String bikes(Model model){
        model.addAttribute("bikes", bikeRepo.findAll());
        return "bikes";
    }

    //TODO: odnośnik thymeleafowy, aby skasować ogłoszenie, jak się jest adminem

    @GetMapping("/bikes/{id}")
    public String oneBike (@PathVariable("id") int id,
                           Model model) {
        BikeModel bike = bikeRepo.getOneById(id);
        model.addAttribute("bike", bike);
        UserModel advertiser = userRepo.getOneById(bike.getUser());
        model.addAttribute("advertiser", advertiser);
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "bikead";
    }

    @GetMapping("/bikes/addbikead")
    public String newBikeAd (Model model) {
        //Model model, bo w templatce jest TH OBJECT - chyba tak by to było
        model.addAttribute("bikeForm", new BikeForm());
        return "addbikead";
    }

    @PostMapping("/bikes/addbikead")
    public String addBikeAdd(@ModelAttribute("bikeForm") BikeForm bikeForm
                             //,
                             //  Model model
                             //@ModelAttribute("bikes") BikeRepo bikeRepo
    )
    {
        BikeModel bikeModel = new BikeModel(bikeForm);
        bikeModel.setUser(userService.getCurrentUser().getId());
        System.out.println("dodaje ogłoszenie user o aj di: " + userService.getCurrentUser().getId());
        bikeRepo.save(bikeModel);
     //   model.addAttribute("bikes", bikeRepo.findAll());
        return "redirect:/bikes";
    }


    //zmienić ze stringa admin na enuma admin
    @GetMapping("/delete/bikes/{id}")
    public String deletePost(@PathVariable("id") int id){
        if (userService.getCurrentUser().getType().equals("admin")){
            bikeRepo.delete(bikeRepo.getOneById(id));
        }
        return "redirect:/bikes";
    }


    @GetMapping("/parts")
    public String parts(){
        return "parts";
    }

    @GetMapping("/clothes")
    public String clothes(){
        return "clothes";
    }

    @GetMapping("/accesories")
    public String accesories(){
        return "accesories";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/newad")
    public String newAd(){
        return "newad";
    }
}

