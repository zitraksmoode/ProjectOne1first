package dmitry.prikols.spring_course.dao;

import dmitry.prikols.spring_course.models.Gym;
import dmitry.prikols.spring_course.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GymDAO {
private final JdbcTemplate jdbcTemplate;
@Autowired
    public GymDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Gym> index() {
        return jdbcTemplate.query("SELECT * FROM Gym", new BeanPropertyRowMapper<>(Gym.class));
    }

    public Gym show(int id) {
        return jdbcTemplate.query("SELECT * FROM Gym WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Gym.class))
                .stream().findAny().orElse(null);
    }

    public void save(Gym gym) {
        jdbcTemplate.update("INSERT INTO Gym(equipment, gym_name) VALUES(?, ?)", gym.getEquipment(),
                gym.getGym_name());
    }

    public void update(int id, Gym updatedGym) {
        jdbcTemplate.update("UPDATE Gym SET equipment=?, gym_name=? WHERE id=?", updatedGym.getEquipment(),
                updatedGym.getGym_name(),  id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Gym WHERE id=?", id);
    }


    public Optional<Person> getGymOwner(int id) {

        return jdbcTemplate.query("SELECT Person.* FROM Gym JOIN Person ON Gym.person_id = Person.id " +
                        "WHERE Gym.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }


    public void release(int id) {
        jdbcTemplate.update("UPDATE Gym SET person_id=NULL WHERE id=?", id);
    }


    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Gym SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }
}



