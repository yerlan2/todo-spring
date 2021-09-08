package springj.authenticas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springj.authenticas.service.JwtUserDetailsService;
import springj.authenticas.payload.JwtRequest;
import springj.authenticas.payload.JwtResponse;
import springj.authenticas.payload.UserDTO;
import springj.authenticas.security.JwtTokenUtil;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;


	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(
		@RequestBody JwtRequest authenticationRequest
	) throws Exception {
		authenticate(
			authenticationRequest.getUsername()
			,authenticationRequest.getPassword()
		);
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user)
	throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}


	private void authenticate(String username, String password)
	throws Exception {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
			);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}