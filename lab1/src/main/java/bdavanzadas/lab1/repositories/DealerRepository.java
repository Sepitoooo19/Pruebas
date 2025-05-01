package bdavanzadas.lab1.repositories;

import bdavanzadas.lab1.entities.DealerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DealerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(DealerEntity dealer) {
        String sql = "INSERT INTO dealers (user_id, name, rut, email, phone, vehicle, plate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, dealer.getUserId(), dealer.getName(), dealer.getRut(), dealer.getEmail(), dealer.getPhone(), dealer.getVehicle(), dealer.getPlate());
    }
}