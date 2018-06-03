package pl.jakubgajewski.GieldaRowerowa.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakubgajewski.GieldaRowerowa.models.BikeModel;
import pl.jakubgajewski.GieldaRowerowa.models.UserModel;

@Repository
public interface BikeRepo extends CrudRepository <BikeModel, Integer> {
    BikeModel getOneById(int id);
    UserModel getOneByUser(int id);
}
