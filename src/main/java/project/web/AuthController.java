package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.repositories.UserRepository;
import project.security.JWTTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody AuthRequest request) {
        try {
            String name = request.getUserName();
            String token = jwtTokenProvider.createToken(name, userRepository.findUserByUserName(name)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found")).getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("userName", name);
            model.put("token", token);

            return ResponseEntity.ok(model);
        }
        catch (AuthenticationException exception) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
