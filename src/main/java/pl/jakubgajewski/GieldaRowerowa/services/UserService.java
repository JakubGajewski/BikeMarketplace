package pl.jakubgajewski.GieldaRowerowa.services;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.jakubgajewski.GieldaRowerowa.models.UserModel;
import pl.jakubgajewski.GieldaRowerowa.models.forms.UserForm;

import javax.annotation.PostConstruct;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@NoArgsConstructor
public class UserService {
    private boolean isLogged;
    private UserModel currentUser;

    //todo - zmienić na guestA!!
    public void setAsGuestByDefault () {
        UserForm userForm = new UserForm(1, "Gość", "Gość", "admin");
        currentUser = new UserModel(userForm);
        }

    @PostConstruct
    public void init() {
        setAsGuestByDefault();
    }
}

