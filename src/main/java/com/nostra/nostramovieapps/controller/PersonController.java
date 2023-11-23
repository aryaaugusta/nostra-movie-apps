package com.nostra.nostramovieapps.controller;

import com.nostra.nostramovieapps.dto.person.PersonDTO;
import com.nostra.nostramovieapps.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/nostramovie/person")
@Api(value = "Nostra Movie Apps API", description = "API to control person in Nostra Movie Apps")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "create", notes = "This method for create person name")
    @PostMapping
    public ResponseEntity<PersonDTO> create(@ApiParam(value = "Input person name ex. director name, producer name etc", required = true)
                                            @RequestBody PersonDTO person) {
        return new ResponseEntity<>(personService.createPerson(person), HttpStatus.OK);
    }
}
