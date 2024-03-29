package com.cenfotec.examen3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cenfotec.examen3.model.AuthenticationRequest;
import com.cenfotec.examen3.repository.UserRepository;
import com.cenfotec.examen3.security.JwtTokenProvider;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;

	@SuppressWarnings("rawtypes")
	@PostMapping("/signin")
	public ResponseEntity signin(@RequestBody AuthenticationRequest data) {

		try {
			String username = data.getUsername();
			String password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}
}
