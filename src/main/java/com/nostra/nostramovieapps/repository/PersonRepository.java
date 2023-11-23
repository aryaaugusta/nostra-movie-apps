package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    /*@Query("SELECT x FROM Person x WHERE x.name like :name")
    Optional<Person> findPersonByNameLike(String name);*/
}
