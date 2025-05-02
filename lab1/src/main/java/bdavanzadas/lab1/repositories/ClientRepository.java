package bdavanzadas.lab1.repositories;

import bdavanzadas.lab1.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(ClientEntity client) {
        String sql = "INSERT INTO clients (user_id, name, rut, email, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, client.getUserId(), client.getName(), client.getRut(), client.getEmail(), client.getPhone(), client.getAddress());
    }
}