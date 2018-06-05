package pl.jakubgajewski.GieldaRowerowa.models.forms;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class BikeForm {
    private int id;
    private int user;
    private String model;
    private String description;
    private float price;
}
