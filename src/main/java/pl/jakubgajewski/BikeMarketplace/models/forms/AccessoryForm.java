package pl.jakubgajewski.BikeMarketplace.models.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessoryForm {
    private int id;
    private int user;
    private String name;
    private String description;
    private float price;
}
