package bdavanzadas.lab1.Controllers;

import bdavanzadas.lab1.entities.ClientEntity;
import bdavanzadas.lab1.entities.DealerEntity;
import bdavanzadas.lab1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, Object> body) {
        String username = (String) body.get("username");
        String password = (String) body.get("password");
        String role = (String) body.get("role");

        if ("CLIENT".equals(role)) {
            ClientEntity client = new ClientEntity();
            client.setName((String) body.get("name"));
            client.setRut((String) body.get("rut"));
            client.setEmail((String) body.get("email"));
            // Registrar cliente
            userService.registerClient(username, password, client);
        } else if ("DEALER".equals(role)) {
            DealerEntity dealer = new DealerEntity();
            dealer.setName((String) body.get("name"));
            dealer.setRut((String) body.get("rut"));
            dealer.setEmail((String) body.get("email"));
            // Registrar distribuidor
            userService.registerDealer(username, password, dealer);
        } else {
            return ResponseEntity.badRequest().body("Rol no válido");
        }

        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (userService.validateCredentials(username, password)) {
            // Generar un token con el rol del usuario (omitiendo la implementación)
            String token = "Token"; // Reemplazar con lógica de generación de JWT
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(401).body("Credenciales inválidas");
    }
}