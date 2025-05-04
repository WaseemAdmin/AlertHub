package com.mst.AlertHub.controller;

import com.mst.AlertHub.service.AuthenticationService;
import com.mst.AlertHub.service.LoginServiceImpl;
import com.mst.AlertHub.services.JwtAuthenticationResponse;
import com.mst.AlertHub.services.SignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("api/auth")
public class LoginUserController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	LoginServiceImpl loginService;

	
	    @CrossOrigin("*")
	    @PostMapping("LoginUser")
	    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest  loginUser) {
		 return ResponseEntity.ok(authenticationService.signIn(loginUser));
	 }
}


