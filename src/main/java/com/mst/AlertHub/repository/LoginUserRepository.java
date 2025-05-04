package com.mst.AlertHub.repository;

import com.mst.AlertHub.beans.LoginUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRepository extends JpaRepository<LoginUsers, Integer> {
}
