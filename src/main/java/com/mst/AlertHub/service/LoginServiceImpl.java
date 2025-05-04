package com.mst.AlertHub.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mst.AlertHub.repository.UserRepository;
import com.mst.AlertHub.beans.LoginUsers;
import com.mst.AlertHub.beans.User;
import com.mst.AlertHub.helper.PasswordHelper;
import com.mst.AlertHub.services.LoginService;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepo;




    @Override
    public ResponseEntity<?> LoginUser(LoginUsers users) {

        User loginUser=userRepo.findUserByEmail(users.getEmail());
        try {
            if (PasswordHelper.validatePassword(users.getPassword(),loginUser.getPassword())) {

                return ResponseEntity.ok("User logged in");
            }

        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> logoutUser(String email) {
        // TODO Auto-generated method stub
        return null;
    }

}
