package com.mst.AlertHub.services;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequest {
	
	private String name;
	private String phoneNumber;
	private String email;
	private String password;

}
