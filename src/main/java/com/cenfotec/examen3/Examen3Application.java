package com.cenfotec.examen3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Examen3Application {

	public static void main(String[] args) {
		SpringApplication.run(Examen3Application.class, args);
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
            	return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        };
    }

}
