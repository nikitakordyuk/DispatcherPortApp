package com.port.DispatcherPortApp.repositories;

import com.port.DispatcherPortApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    @Transactional
    Optional<Person> findByUsername(String username);
}
