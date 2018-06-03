package pl.jakubgajewski.GieldaRowerowa.models;


import lombok.*;
import pl.jakubgajewski.GieldaRowerowa.models.forms.BikeForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bike")
@Data
@NoArgsConstructor
public class BikeModel {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    private int id;
    private int user;
    private String model;
    private String description;
    private float price;

    public BikeModel (BikeForm bikeForm) {
        this.id = bikeForm.getId();
        this.user = bikeForm.getUser();
        this.description = bikeForm.getDescription();
        this.model = bikeForm.getModel();
        this.price = bikeForm.getPrice();
    }


}
