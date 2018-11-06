package pl.jakubgajewski.BikeMarketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubgajewski.BikeMarketplace.models.AccessoryModel;
import pl.jakubgajewski.BikeMarketplace.models.UserModel;
import pl.jakubgajewski.BikeMarketplace.models.forms.AccessoryForm;
import pl.jakubgajewski.BikeMarketplace.models.repositories.AccessoryRepo;
import pl.jakubgajewski.BikeMarketplace.models.repositories.UserRepo;
import pl.jakubgajewski.BikeMarketplace.models.services.UserService;

//TODO: creat common interface for adding new announcements
//delegate methods: description, price, model

@Controller
public class AccessoriesController {

    final
    AccessoryRepo accessoryRepo;

    final
    UserRepo userRepo;

    final
    UserService userService;

    @Autowired
    public AccessoriesController(AccessoryRepo accessoryRepo, UserRepo userRepo, UserService userService) {
        this.accessoryRepo = accessoryRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/accessories")
    public String accessories(Model model){
        model.addAttribute("accessories", accessoryRepo.findAll());
        return "accessories";
    }

    @GetMapping("/accessories/{id}")
    public String oneAccessory (@PathVariable("id") int id,
                           Model model) {
        AccessoryModel accessory = accessoryRepo.getOneById(id);
        model.addAttribute("accessory", accessory);
        UserModel advertiser = userRepo.getOneById(accessory.getUser());
        model.addAttribute("advertiser", advertiser);
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "accessoryad";
    }

    @GetMapping("/accessories/addaccessoryad")
    public String newAccessoryAd (Model model) {
        //Model model, bo w templatce jest TH OBJECT - chyba tak by to było
        model.addAttribute("accessoryForm", new AccessoryForm());
        return "addaccessoryad";
    }

    @PostMapping("/accessories/addaccessoryad")
    public String addAccessoryAdd(@ModelAttribute("accessoryForm") AccessoryForm accessoryForm,
                             @ModelAttribute ("info") String info
                             //,
                             //  Model model
                             //@ModelAttribute("accessories") AccessoryRepo accessoryRepo
    )
    {
        AccessoryModel accessoryModel = new AccessoryModel(accessoryForm);
        if (accessoryForm.getDescription().length() == 0) {
            info = "Wpisz opis";
            return "addaccessoryad";
        }
        accessoryModel.setUser(userService.getCurrentUser().getId());
        accessoryRepo.save(accessoryModel);
        //   model.addAttribute("accessories", accessoryRepo.findAll());
        return "redirect:/accessories";
    }


    //zmienić ze stringa admin na enuma admin
    @GetMapping("/delete/accessories/{id}")
    public String deletePost(@PathVariable("id") int id){
        if (userService.getCurrentUser().getType().equals("admin")){
            accessoryRepo.delete(accessoryRepo.getOneById(id));
        }
        return "redirect:/accessories";
    }
}

