package pl.jakubgajewski.GieldaRowerowa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakubgajewski.GieldaRowerowa.models.UserModel;
import pl.jakubgajewski.GieldaRowerowa.models.forms.UserForm;
import pl.jakubgajewski.GieldaRowerowa.models.repositories.BikeRepo;
import pl.jakubgajewski.GieldaRowerowa.models.repositories.UserRepo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jakubgajewski.GieldaRowerowa.services.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    final
    UserRepo userRepo;

    final
    BikeRepo bikeRepo;

    final
    UserService userService;

    @Autowired
    public UserController (BikeRepo bikeRepo, UserRepo userRepo, UserService userService) {
        this.bikeRepo = bikeRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam("login") String login,
                            @RequestParam ("password") String password,
                            Model model){
        if (userRepo.existsByLoginAndPassword(login, password)) {
            userService.setLogged(true);
            userService.setCurrentUser(userRepo.getOneByLogin(login));
            return "dashboard";
        }
        model.addAttribute("info", "Dane logowania niepoprawne");
        return "login";
    }
//TODO: Podpiąć do templatek
    @GetMapping("/logout")
    public String logout(){
        userService.setAsGuest();
        return "dashboard";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    //todo: walidacja, czy powtórzone jest hasło jest takie samo
    //todo: login handler, podpisywanie wiadomości
    @PostMapping("/register")
    public String registerPost(@Valid @RequestParam("login") String login,
                            @RequestParam ("password") String password,
                            @RequestParam ("repeatPassword") String repeatPassword,
                            Model model)
    {
        UserForm userForm = new UserForm();
        //TODO: sprwadzić, czy już nie zajęty
        if (!userRepo.existsByLogin(login) && password.equals(repeatPassword)) {
            userForm.setLogin(login);
            userForm.setPassword(password);
            userForm.setType("regular");
            UserModel userModel = new UserModel(userForm);
            userRepo.save(userModel);
            userService.setCurrentUser(userModel);
            userService.setLogged(true);
            return "redirect:/";
        } else if (userRepo.existsByLogin(login)) {
            model.addAttribute("info", "Wybrany login jest już zajęty");
            return "register";
        }
        model.addAttribute("info", "Błędne powtórzenie hasła");
        return "register";
    }

    @GetMapping("/userlist")
    public String userlist(Model model){
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "userlist";
    }

    @GetMapping("/delete/userlist/{id}")
    public String deletePost(@PathVariable("id") int id){
        if (userService.getCurrentUser().getType().equals("admin")){
            userRepo.delete(userRepo.getOneById(id));
            bikeRepo.delete(bikeRepo.findByUser(id));
        }
        return "redirect:/userlist";
    }



    //todo: walidacja, czy powtórzone jest hasło jest takie samo
}
