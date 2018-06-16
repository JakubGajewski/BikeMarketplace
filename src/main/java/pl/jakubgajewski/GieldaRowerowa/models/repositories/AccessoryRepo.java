package pl.jakubgajewski.GieldaRowerowa.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakubgajewski.GieldaRowerowa.models.AccessoryModel;
import pl.jakubgajewski.GieldaRowerowa.models.BikeModel;

import java.util.List;

@Repository
public interface AccessoryRepo extends CrudRepository <AccessoryModel, Integer> {
    AccessoryModel getOneById(int id);
    List<AccessoryModel> findByUser(int id);
}
