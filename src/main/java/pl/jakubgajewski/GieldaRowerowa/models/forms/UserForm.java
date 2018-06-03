package pl.jakubgajewski.GieldaRowerowa.models.forms;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class UserForm {
    private int id;
    private String login;
    private String password;
    private String type;
}
//TODO - zrobić enuma dla type;
//TODO - aby było widać, kto dodał ogłoszenie