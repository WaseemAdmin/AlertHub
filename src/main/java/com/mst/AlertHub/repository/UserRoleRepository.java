package com.mst.AlertHub.repository;

import com.mst.AlertHub.beans.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query("SELECT ur.role.roleName FROM UserRole ur WHERE ur.user.id = :userId")
    Set<String> findRoleNamesByUserId(@Param("userId") int userId);
}
