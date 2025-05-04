package com.mst.AlertHub.controller;

import com.mst.AlertHub.beans.Role;
import com.mst.AlertHub.exceptions.RoleExceptions;
import com.mst.AlertHub.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/role")
public class RoleController {
	
	
	
	@Autowired
    private RoleService roleService;

    // Create a new role
	@PostMapping("/create_role")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            Role newRole = roleService.createRole(role);
            return new ResponseEntity<>(newRole, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (RoleExceptions.RoleAlreadyExistsException ex) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    // Update an existing role by ID
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role role) {
        try {
            Role updatedRole = roleService.updateRole(id, role);
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } catch (RoleExceptions.RoleNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a role by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        boolean isDeleted = roleService.deleteRole(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get a role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        Role role = roleService.getRolebyId(id);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get all roles
    @GetMapping("/all_roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}
