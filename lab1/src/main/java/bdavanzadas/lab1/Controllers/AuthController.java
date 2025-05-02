package bdavanzadas.lab1.Controllers;

import bdavanzadas.lab1.entities.ClientEntity;
import bdavanzadas.lab1.entities.DealerEntity;
import bdavanzadas.lab1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bdavanzadas.lab1.entities.UserEntity;
import bdavanzadas.lab1.Security.JwtUtil;

import org.springframework.http.HttpStatus;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> body) {
        String username = (String) body.get("username");
        String password = (String) body.get("password");
        String role = (String) body.get("role");

        if ("ADMIN".equals(role)) {
            userService.registerAdmin(username, password);
        } else if ("CLIENT".equals(role)) {
            String name = (String) body.get("name");
            String rut = (String) body.get("rut");
            String email = (String) body.get("email");
            userService.registerClient(username, password, name, rut, email);
        } else if ("DEALER".equals(role)) {
            String name = (String) body.get("name");
            String rut = (String) body.get("rut");
            String email = (String) body.get("email");
            userService.registerDealer(username, password, name, rut, email);
        }

        return ResponseEntity.ok("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        UserEntity user = userService.validateCredentials(username, password);
        if (user != null) {
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}