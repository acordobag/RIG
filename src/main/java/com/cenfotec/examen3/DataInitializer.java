package com.cenfotec.examen3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.cenfotec.examen3.model.User;
import com.cenfotec.examen3.repository.PaisRepository;
import com.cenfotec.examen3.repository.ProvinciaRepository;
import com.cenfotec.examen3.repository.UserRepository;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

	@Autowired
    PaisRepository pais;
	@Autowired
    ProvinciaRepository provincia;
	@Autowired
	UserRepository users;
	@Autowired
	PasswordEncoder passwordEncoder;
    

    @Override
    public void run(String... args) throws Exception {
//        this.users.save(User.builder()
//            .username("user")
//            .password(this.passwordEncoder.encode("password"))
//            .roles(Arrays.asList( "ROLE_USER"))
//            .build()
//        );
//
//        this.users.save(User.builder()
//            .username("admin")
//            .password(this.passwordEncoder.encode("password"))
//            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
//            .build()
//        );

        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
