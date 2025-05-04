package com.mst.AlertHub.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mst.AlertHub.repository.RoleRepository;

import com.mst.AlertHub.beans.Role;
import com.mst.AlertHub.exceptions.RoleExceptions;
import com.mst.AlertHub.exceptions.UserExceptions;
import com.mst.AlertHub.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepo;

    @Override
    public Role createRole(Role role) {
        if(role==null) {
            throw new IllegalArgumentException("User or User ID cannot be null");
        }

        Role existingRole= roleRepo.findRoleByid(role.getId());
        if (existingRole == null) {
            return roleRepo.save(role);
        } else {
            throw new RoleExceptions.RoleAlreadyExistsException("User with ID " + role.getId() + " already exists");
        }
    }

    @Override
    public Role updateRole(int id,Role role) {
        Role existingRole = roleRepo.findRoleByid(id);

        if (existingRole == null) {
            throw new UserExceptions.UserNotFoundException("User with ID " + id + " not found");
        }

        existingRole.setRoleName(role.getRoleName());




        return roleRepo.save(existingRole);
    }

    @Override
    public boolean deleteRole(int id) {
        Role existingRole = roleRepo.findRoleByid(id);
        if (existingRole == null) {
            return false;
        }
        roleRepo.delete(existingRole);
        return true;
    }

    @Override
    public Role getRolebyId(int id) {
        return roleRepo.findRoleByid(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

}
