package com.nostra.nostramovieapps.service.impl;

import com.nostra.nostramovieapps.dto.person.PersonDTO;
import com.nostra.nostramovieapps.dto.person.PersonDetailDTO;
import com.nostra.nostramovieapps.entity.enums.Status;
import com.nostra.nostramovieapps.entity.person.Person;
import com.nostra.nostramovieapps.entity.person.PersonDetail;
import com.nostra.nostramovieapps.repository.PersonDetailRepository;
import com.nostra.nostramovieapps.repository.PersonRepository;
import com.nostra.nostramovieapps.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonDetailRepository personDetailRepository;

    @Override
    @Transactional
    public PersonDTO createPerson(PersonDTO input) {
        Person person = new Person();
        BeanUtils.copyProperties(input, person);
        person.setStatus(Status.ACTIVE);
        person.setCreatedBy("Arya");
        person.setCreatedAt(ZonedDateTime.now());
        Person saved = personRepository.save(person);

        if (!CollectionUtils.isEmpty(input.getPersonDetails())) {
            for (PersonDetailDTO detail : input.getPersonDetails()) {
                PersonDetail personDetail = new PersonDetail();
                BeanUtils.copyProperties(detail, personDetail);
                personDetail.setPerson(saved);
                personDetailRepository.save(personDetail);
            }
        }

        input.setId(saved.getId());
        input.setVersion(saved.getVersion());
        return input;
    }
}
