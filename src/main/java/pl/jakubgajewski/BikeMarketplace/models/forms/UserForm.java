package pl.jakubgajewski.BikeMarketplace.models.forms;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    private int id;
    private String login;
    private String password;
    private String type;
}
//TODO - zrobić enuma dla type;
//TODO - aby było widać, kto dodał ogłoszenie