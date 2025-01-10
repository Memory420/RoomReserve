package com.memory.roomreserve.repository;

import com.memory.roomreserve.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String name);

    User findById(long id);
    @Query("SELECT u.id FROM User u")
    List<Long> findAllIds();
}
