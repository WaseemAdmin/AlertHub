package com.mst.AlertHub.services;

import com.mst.AlertHub.beans.Role;

import java.util.List;

public interface RoleService {
	
	Role createRole(Role role);
	Role updateRole(int id,Role  role);
	boolean deleteRole(int id);
	Role getRolebyId(int id);
	List<Role> getAllRoles();
	

}
