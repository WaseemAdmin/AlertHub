package com.mst.AlertHub.services;

import com.mst.AlertHub.beans.LoginUsers;
import org.springframework.http.ResponseEntity;



public interface LoginService {
	
	ResponseEntity<?> LoginUser(LoginUsers users);

    ResponseEntity<?> logoutUser(String email);

}
