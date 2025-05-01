package bdavanzadas.lab1.services;

import bdavanzadas.lab1.entities.ClientEntity;
import bdavanzadas.lab1.entities.DealerEntity;
import bdavanzadas.lab1.entities.UserEntity;
import bdavanzadas.lab1.repositories.ClientRepository;
import bdavanzadas.lab1.repositories.DealerRepository;
import bdavanzadas.lab1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void registerClient(String username, String password, ClientEntity client) {
        // 1. Crear el usuario
        String encodedPassword = encoder.encode(password);
        UserEntity user = new UserEntity(0, username, encodedPassword, "CLIENT", null, null);
        userRepository.save(user);

        // 2. Asociar el usuario con el cliente
        client.setUserId(user.getId());
        clientRepository.save(client);
    }

    public void registerDealer(String username, String password, DealerEntity dealer) {
        // 1. Crear el usuario
        String encodedPassword = encoder.encode(password);
        UserEntity user = new UserEntity(0, username, encodedPassword, "DEALER", null, null);
        userRepository.save(user);

        // 2. Asociar el usuario con el dealer
        dealer.setUserId(user.getId());
        dealerRepository.save(dealer);
    }

    public boolean validateCredentials(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        return user != null && encoder.matches(password, user.getPassword());
    }
}