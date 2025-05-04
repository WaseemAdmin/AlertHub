package com.mst.AlertHub.repository;


import com.mst.AlertHub.beans.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByid(int id);
    Role findRoleByRoleName(String name);

}
