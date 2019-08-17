package com.cenfotec.examen3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.cenfotec.examen3.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
