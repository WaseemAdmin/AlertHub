package com.mst.AlertHub.repository;


import com.mst.AlertHub.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(int id);
    User findUserByEmail(String email);
    Optional<User> findByEmail (String email);


}
