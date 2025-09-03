package com.wipro.UserAuthService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.UserAuthService.entities.User;
import com.wipro.UserAuthService.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 Optional<User> findFirstByEmail(String username);

	 Optional<User> findByUserRole(UserRole admin);

}
