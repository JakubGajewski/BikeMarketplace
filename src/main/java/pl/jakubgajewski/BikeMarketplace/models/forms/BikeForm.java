package pl.jakubgajewski.BikeMarketplace.models.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BikeForm {
    private int id;
    private int user;
    private String model;
    private String description;
    private float price;
}
