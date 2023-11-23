package com.nostra.nostramovieapps.service.impl;

import com.nostra.nostramovieapps.dto.person.PersonDTO;
import com.nostra.nostramovieapps.repository.PersonDetailRepository;
import com.nostra.nostramovieapps.repository.PersonRepository;
import com.nostra.nostramovieapps.service.PersonService;
import com.nostra.nostramovieapps.util.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonDetailRepository personDetailRepository;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    @Transactional
    public PersonDTO createPerson(PersonDTO input) {
        return input;
    }
}
