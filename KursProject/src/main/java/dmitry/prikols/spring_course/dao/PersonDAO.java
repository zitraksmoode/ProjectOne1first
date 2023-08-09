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
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name,age,sex,email) VALUES(?, ?, ?, ?)",person.getFull_name() ,person.getAge(),
                person.getSex(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=? ,age=?, sex=?, email=? WHERE id=?", updatedPerson.getFull_name() ,updatedPerson.getAge(),
                updatedPerson.getSex(), updatedPerson.getEmail(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public List<Gym> getGymByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Gym WHERE person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Gym.class));
    }
}

