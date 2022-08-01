package com.bosonit.JDBC;

import java.util.List;

public interface PersonRepository {
    int save(Person person);
    int update(Person person);
    Person findById(Long id);
    int deleteById(Long id);
    List<Person> findAll();
    List<Person> findByAdmin(boolean admin);
    List<Person> findByName(String name);
    int deleteAll();
}
