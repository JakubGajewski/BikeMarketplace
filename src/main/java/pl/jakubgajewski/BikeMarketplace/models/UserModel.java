package pl.jakubgajewski.BikeMarketplace.models;

import lombok.*;
import pl.jakubgajewski.BikeMarketplace.models.forms.UserForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    private int id;
    private String login;
    private String password;
    private String type;

    public UserModel (UserForm userForm) {
        this.id = userForm.getId();
        this.login = userForm.getLogin();
        this.password = userForm.getPassword();
        this.type = userForm.getType();
    }
}
