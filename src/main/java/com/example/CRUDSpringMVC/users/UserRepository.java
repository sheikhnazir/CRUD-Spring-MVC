package com.example.CRUDSpringMVC.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByEnabled(boolean enabled);

    Long countById(Integer id);
}

