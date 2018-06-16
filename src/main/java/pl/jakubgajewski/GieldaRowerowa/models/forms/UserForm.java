package pl.jakubgajewski.GieldaRowerowa.models.forms;

import lombok.*;

import javax.validation.constraints.Size;

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