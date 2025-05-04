package com.mst.AlertHub.service;



import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mst.AlertHub.repository.RoleRepository;
import com.mst.AlertHub.repository.UserRepository;
import com.mst.AlertHub.repository.UserRoleRepository;
import com.mst.AlertHub.beans.Role;
import com.mst.AlertHub.beans.User;
import com.mst.AlertHub.beans.UserRole;
import com.mst.AlertHub.exceptions.UserExceptions;
import com.mst.AlertHub.services.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRoleRepository userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

//		@Autowired
//		private RestTemplate restTemplate;





    @Value("${metrics.service.url}") // E.g., http://metrics-service
    private String metricsServiceUrl;

    @Override
    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User or User ID cannot be null");
        }

        User existingUser = userRepo.findUserById(user.getId());

        if (existingUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user.setUserType("");
            return userRepo.save(user);
        } else {
            throw new UserExceptions.UserAlreadyExistsException("User with ID " + user.getId() + " already exists");
        }
    }


    @Override
    public User updateUser(int id,User updatedUser) {

        User existingUser = userRepo.findUserById(id);

        if (existingUser == null) {
            throw new UserExceptions.UserNotFoundException("User with ID " + id + " not found");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());



        return userRepo.save(existingUser);

    }

    @Override
    public boolean deleteUser(int id) {
        User existingUser = userRepo.findUserById(id);
        if (existingUser == null) {
            return false;
        }
        userRepo.delete(existingUser);
        return true;
    }


    @Override
    public User getUserById(int id) {
        return userRepo.findUserById(id);
    }


    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }





    @Override
    public User assignRoleToUser(int userId, int roleId) {
        User user = userRepo.findUserById(userId);

        Role role = roleRepo.findRoleByid(roleId);


        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleRepo.save(userRole);

        return userRepo.findById(userId).get();


    }



//	public ResponseEntity<Map<String, Object>> createMetricForUser(int userId, MetricRequest metricRequest) {
//	    boolean hasPermission = checkUserPermission(userId, "createMetric");
//	    if (!hasPermission) {
//	        throw new AccessDeniedException("User does not have permission to create metrics");
//	    }
//
//	    HttpEntity<MetricRequest> requestEntity = new HttpEntity<>(metricRequest);
//	    ResponseEntity<Map> response = restTemplate.exchange(
//	        metricsServiceUrl + "/createmetric",
//	        HttpMethod.POST,
//	        requestEntity,
//	        Map.class
//	    );
//
//	    return new ResponseEntity<>(response.getBody(), response.getStatusCode());
//	}





    private boolean checkUserPermission(int userId, String requiredPermission) {
        Set<String> userPermissions = userRoleRepo.findRoleNamesByUserId(userId);
        return userPermissions.contains(requiredPermission);
    }



}
