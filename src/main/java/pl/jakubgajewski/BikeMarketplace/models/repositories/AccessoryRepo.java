package pl.jakubgajewski.BikeMarketplace.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakubgajewski.BikeMarketplace.models.AccessoryModel;

import java.util.List;

@Repository
public interface AccessoryRepo extends CrudRepository <AccessoryModel, Integer> {
    AccessoryModel getOneById(int id);
    List<AccessoryModel> findByUser(int id);
}
