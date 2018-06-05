package pl.jakubgajewski.GieldaRowerowa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakubgajewski.GieldaRowerowa.models.BikeModel;
import pl.jakubgajewski.GieldaRowerowa.models.UserModel;
import pl.jakubgajewski.GieldaRowerowa.models.forms.UserForm;
import pl.jakubgajewski.GieldaRowerowa.models.repositories.UserRepo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jakubgajewski.GieldaRowerowa.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Controller
public class UserController {

    final
    UserRepo userRepo;

    final
    UserService userService;

    @Autowired
    public UserController (UserRepo userRepo, UserService userService) {
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
            //jeśli jest ten model - spring wie, że chcemy wysłać danę do templatki i pozwala
        // na ich wysyłkę; potrzebny przez to "info"
        boolean exist = userRepo.existsByLoginAndPassword(login, password);
        if (exist) {
            userService.setLogged(true);
            userService.setCurrentUser(userRepo.getOneByLogin(login));
            System.out.println("Zalogowano usera o aj di: " + userService.getCurrentUser().getId());
            return "dashboard";
        }
        model.addAttribute("info", "Dane logowania niepoprawne");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    //todo: walidacja, czy powtórzone jest hasło jest takie samo
    //todo: login handler, podpisywanie wiadomości
    @PostMapping("/register")
    public String registerPost(@RequestParam("login") String login,
                            @RequestParam ("password") String password,
                            @RequestParam ("repeatPassword") String repeatPassword
    ){

                               //Model model - często tu bywa, ale po co?
        UserForm userForm = new UserForm();
        //TODO: sprwadzić, czy już nie zajęty
        if (password.equals(repeatPassword)) {
            userForm.setLogin(login);
            userForm.setPassword(password);
            userForm.setType("regular");
            UserModel userModel = new UserModel(userForm);
            userRepo.save(userModel);
            System.out.println("DOBRZE!");

        }
        //TODO: info czy się powiodło; info że login jest zajęty; walidacja hasła
        System.out.println("zupa");
        System.out.println(password);
        return "redirect:/";
    }
}
