package pl.jakubgajewski.GieldaRowerowa.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakubgajewski.GieldaRowerowa.models.UserModel;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository <UserModel, Integer> {
    Optional<UserModel> findByLoginAndPassword(String login, String password);
    boolean existsByLoginAndPassword(String login, String password);
    UserModel getOneById(int id);
    UserModel getOneByLogin(String login);
}
