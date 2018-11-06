package pl.jakubgajewski.BikeMarketplace.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jakubgajewski.BikeMarketplace.models.forms.AccessoryForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accessory")
@Data
@NoArgsConstructor
public class AccessoryModel {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    private int id;
    private int user;
    private String name;
    private String description;
    private float price;

    public AccessoryModel(AccessoryForm accesoryForm) {
        this.id = accesoryForm.getId();
        this.user = accesoryForm.getUser();
        this.description = accesoryForm.getDescription();
        this.name = accesoryForm.getName();
        this.price = accesoryForm.getPrice();
    }


}
