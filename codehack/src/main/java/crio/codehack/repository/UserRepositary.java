package crio.codehack.repository;

import crio.codehack.model.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositary extends MongoRepository<User,String> {

    User findByUserId(String userId);

    @SuppressWarnings("null")
    List<User> findAll();

    boolean deleteByUserId(String userId);

}