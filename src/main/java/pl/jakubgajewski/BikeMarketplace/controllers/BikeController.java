package pl.jakubgajewski.BikeMarketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubgajewski.BikeMarketplace.models.BikeModel;
import pl.jakubgajewski.BikeMarketplace.models.UserModel;
import pl.jakubgajewski.BikeMarketplace.models.forms.BikeForm;
import pl.jakubgajewski.BikeMarketplace.models.repositories.BikeRepo;
import pl.jakubgajewski.BikeMarketplace.models.repositories.UserRepo;
import pl.jakubgajewski.BikeMarketplace.models.services.UserService;

@Controller
public class BikeController {

    final
    BikeRepo bikeRepo;

    final
    UserRepo userRepo;

    final
    UserService userService;

    @Autowired
    public BikeController(BikeRepo bikeRepo, UserRepo userRepo, UserService userService) {
        this.bikeRepo = bikeRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/bikes")
    public String bikes(Model model){
        model.addAttribute("bikes", bikeRepo.findAll());
        return "bikes";
    }

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
    public String addBikeAdd(@ModelAttribute("bikeForm") BikeForm bikeForm,
                             @ModelAttribute ("info") String info
                             //,
                             //  Model model
                             //@ModelAttribute("bikes") BikeRepo bikeRepo
    )
    {
        BikeModel bikeModel = new BikeModel(bikeForm);
        if (bikeForm.getDescription().length() == 0) {
            info = "Wpisz opis";
            return "addbikead";
        }
        bikeModel.setUser(userService.getCurrentUser().getId());
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

}

