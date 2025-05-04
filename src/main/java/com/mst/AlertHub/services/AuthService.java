package com.mst.AlertHub.services;

public interface AuthService {
	
	JwtAuthenticationResponse signUp (SignUpRequest request);
	JwtAuthenticationResponse signIn(SignInRequest request);

}
