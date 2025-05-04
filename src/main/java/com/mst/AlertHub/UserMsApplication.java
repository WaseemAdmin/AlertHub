package com.mst.AlertHub;

import com.mst.AlertHub.repository.RoleRepository;
import com.mst.AlertHub.repository.UserRepository;
import com.mst.AlertHub.beans.Role;
import com.mst.AlertHub.beans.User;
import com.mst.AlertHub.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition
public class UserMsApplication implements CommandLineRunner {
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	 @Value("${systemAdmin.password}")
	 private String AdminPassword;
	 
	public static void main(String[] args) {
		SpringApplication.run(UserMsApplication.class, args);
		System.out.println("Server is Ready");
	}

	

	@Override
	public void run(String... args) throws Exception {
		String[] actionsArray = {
	            "createAction",
	            "updateAction",
	            "deleteAction",
	            "createMetric",
	            "updateMetric",
	            "deleteMetric",
	            "triggerScan",
	            "triggerProcess",
	            "triggerEvaluation",
	            "read"
	        };
	        
		for (String action : actionsArray) {
	        Role existingRole = roleRepo.findRoleByRoleName(action);
	        
	        if (existingRole == null) {
	            Role role = new Role();
	            role.setRoleName(action);
	            roleRepo.save(role);
	            System.out.println("Added role: " + action);
	        } else {
	            System.out.println("Role already exists: " + action);
	        }
	     }
		User adminUser=new User();
		adminUser.setEmail("admin@gmail.com");
		adminUser.setName("Admin");
		adminUser.setPassword(passwordEncoder.encode(AdminPassword));
		adminUser.setUserType("Admin");
		if(userRepo.findUserByEmail(adminUser.getEmail())==null) {
			userRepo.save(adminUser);
		}
		
		else {
			System.out.println("Admin Already Exsists, no need to add");
		}
		for(int i=0;i<10;i++) {
			userServiceImpl.assignRoleToUser(adminUser.getId() ,i);
		}
		
	}

}
