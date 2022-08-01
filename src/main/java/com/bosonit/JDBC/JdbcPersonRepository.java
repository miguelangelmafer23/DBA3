package com.bosonit.JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPersonRepository implements PersonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int save(Person person) {
        return jdbcTemplate.update("INSERT INTO person (name, surname, admin) VALUES(?,?,?)",
                new Object[] { person.getName(), person.getSurname(), person.isAdmin() });
    }
    @Override
    public int update(Person person) {
        return jdbcTemplate.update("UPDATE person SET name=?, surname=?, admin=? WHERE id=?",
                new Object[] { person.getName(), person.getSurname(), person.isAdmin(), person.getId() });
    }
    @Override
    public Person findById(Long id) {
        try {
            Person person = jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Person.class), id);
            return person;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * from person", BeanPropertyRowMapper.newInstance(Person.class));
    }
    @Override
    public List<Person> findByAdmin(boolean admin) {
        return jdbcTemplate.query("SELECT * from person WHERE admin=?",
                BeanPropertyRowMapper.newInstance(Person.class), admin);
    }
    @Override
    public List<Person> findByName(String name) {
        String q = "SELECT * from person WHERE name ILIKE '%" + name + "%'";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Person.class));
    }
    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from person");
    }
}
