package pl.jakubgajewski.BikeMarketplace.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakubgajewski.BikeMarketplace.models.BikeModel;
import java.util.List;

@Repository
public interface BikeRepo extends CrudRepository <BikeModel, Integer> {
    BikeModel getOneById(int id);
    List<BikeModel> findByUser (int id);
}
