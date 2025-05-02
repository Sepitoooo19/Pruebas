package bdavanzadas.lab1.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bdavanzadas.lab1.entities.ClientEntity;
import bdavanzadas.lab1.repositories.ClientRepository;
import bdavanzadas.lab1.entities.UserEntity;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void registerClient(String name, String rut, String email, String phone, String address, UserEntity user) {
        ClientEntity client = new ClientEntity();
        client.setName(name);
        client.setRut(rut);
        client.setEmail(email);
        client.setPhone(phone);
        client.setAddress(address); // Asegúrate de que este campo no sea nulo
        client.setUserId(user.getId());
        clientRepository.save(client);
    }


}
