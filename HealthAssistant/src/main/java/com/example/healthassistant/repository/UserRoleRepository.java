package com.example.healthassistant.repository;

import com.example.healthassistant.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findUserRoleByUserId(Long id);

    void deleteByUserId(Long id);

}
