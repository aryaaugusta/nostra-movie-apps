package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    @Query("SELECT x FROM Person x WHERE x.name like :name")
    Optional<Person> findByName(String name);
}
