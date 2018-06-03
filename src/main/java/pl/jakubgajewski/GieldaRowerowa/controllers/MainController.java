package pl.jakubgajewski.GieldaRowerowa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String dashboard(){
        System.out.println("aj di userservice: " + userService.getId());
        return "dashboard";
    }

    @GetMapping("/bikes")
    public String bikes(Model model){
        System.out.println("aj di userservice: " + userService.getId());
        model.addAttribute("bikes", bikeRepo.findAll());
        return "bikes";
    }

    @GetMapping("/bikes/{id}")
    public String oneBike (@PathVariable("id") int id,
                           Model model) {
        BikeModel bikeFromAd = bikeRepo.getOneById(id);
        model.addAttribute("bike", bikeFromAd);
        UserModel advertiser = userRepo.getOneById(bikeFromAd.getUser());
        model.addAttribute("user", advertiser);
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
        bikeModel.setUser(userService.getId());
        System.out.println("dodaje ogłoszenie user o aj di: " +userService.getId());
        bikeRepo.save(bikeModel);
     //   model.addAttribute("bikes", bikeRepo.findAll());
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